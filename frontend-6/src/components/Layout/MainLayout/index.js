/* eslint-disable no-nested-ternary */
/* --------------------------------------------------------
* Author Trần Đức Tiến
* Email tientran0019@gmail.com
* Phone 0972970075
*
* Created: 2020-03-01 17:38:42
*------------------------------------------------------- */

import PropTypes from 'prop-types';
import { useEffect, useState } from 'react';

import Image from 'next/image';
import Link from 'next/link';
import Router from 'next/router';

import { BackTop, Button, Layout } from 'antd';

import AvatarDropDown from 'src/components/AvatarDropDown';
import Footer from 'src/components/Layout/Footer';
import Header from 'src/components/Layout/Header';
import Sidebar from 'src/components/Layout/Sidebar';
import Notifications from 'src/components/Notifications';

import CookieAlert from 'src/components/CookieAlert';

import BreadcrumbDn from '../BreadcrumbDn';
import classes from './style.module.less';
import { LoginService } from 'src/service/LoginService';
import LoginModal from 'src/containers/Auth/LoginModal';

const { Content, Sider } = Layout;

const propTypes = {
	children: PropTypes.any,
};

const defaultProps = {
	children: null,
};

const MainLayout = (props) => {

	const loginService = new LoginService();

	const { children } = props;

	const [modalVisible, setModalVisible] = useState(false);

	const [collapsed, setCollapsed] = useState(true);
	const [mobiShow, setMobiShow] = useState(false);
	const [broken, setBroken] = useState(false);

	useEffect(() => {
		const handleRouteChange = url => {
			setMobiShow(false);
		};

		Router.events.on('routeChangeStart', handleRouteChange);
		return () => {
			Router.events.off('routeChangeStart', handleRouteChange);
		};
	}, []);

	const handleToggle = () => {
		if (broken) {
			setMobiShow(!mobiShow);
		} else {
			setCollapsed(!collapsed);
		}
	};

	const showLoginModal = () => {
		setModalVisible(true);
	};

	const closeLoginModal = () => {
		setModalVisible(false);
	};

	return (
		<>
			<Layout
				style={{
					minHeight: '100vh',
				}}
				className={classes.root}
			>
				<Header
					style={{
						left: 0,
					}}
				>
					<Link href="/" className={classes.headerLeft}>
						<div className={classes.logoCenter}>
							<Image
								src="/images/logo.png"
								alt="Logo"
								width={35}
								height={35}
							/>
							<span>LOTERIA</span>
						</div>
					</Link>

					<Sidebar />

					{loginService.autenticado() ?
						<div className={classes.headerRight}>
							<AvatarDropDown />
							<Notifications />
						</div>
						:
						<div className={classes.headerRight}>

							<Button onClick={showLoginModal}>Entrar</Button>
						</div>
					}
				</Header>

				<Content
					style={{
						padding: '0 230px',
					}}
				>

					<BreadcrumbDn />

					<Layout className={classes.siteLayout}>

						{mobiShow && broken && <div className={classes.overlay} onClick={() => setMobiShow(false)} />}

						<Content style={{ margin: 20, }}>
							{children}
						</Content>

					</Layout>
				</Content>

				<Footer />

			</Layout>

			<BackTop />
			<CookieAlert />

			<LoginModal
				visible={modalVisible}
				onClose={closeLoginModal}
			/>
		</>
	);
};

MainLayout.propTypes = propTypes;
MainLayout.defaultProps = defaultProps;

export default MainLayout;
