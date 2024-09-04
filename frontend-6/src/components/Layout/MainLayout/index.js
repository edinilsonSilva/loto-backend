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

import CookieAlert from 'src/components/CookieAlert';

import { ShoppingCartOutlined } from '@ant-design/icons';
import LoginModal from 'src/containers/Auth/LoginModal';
import { LoginService } from 'src/service/LoginService';
import BreadcrumbDn from '../BreadcrumbDn';
import classes from './style.module.less';

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
								src="/images/sorte.png"
								alt="Logo"
								width={55}
								height={35}
							/>
							<span>Loteria da Sorte</span>
						</div>
					</Link>

					<Sidebar />

					{loginService.autenticado() ?
						<div className={classes.headerRight}>
							<AvatarDropDown />
							<Link href="/meu-carrinho" passHref>
								<Button icon={<ShoppingCartOutlined />} shape="round" style={{ marginLeft: 10 }} />
							</Link>

						</div>
						:
						<div className={classes.headerRight}>
							<Button onClick={showLoginModal} shape="round">Entrar</Button>

							<Link href="/meu-carrinho" passHref>
								<Button icon={<ShoppingCartOutlined />} shape="round" style={{ marginLeft: 10 }} />
							</Link>
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
