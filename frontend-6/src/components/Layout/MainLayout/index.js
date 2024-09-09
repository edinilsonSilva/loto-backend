import PropTypes from 'prop-types';
import { useEffect, useState } from 'react';

import Image from 'next/image';
import Link from 'next/link';
import Router from 'next/router';

import { BackTop, Button, Drawer, Layout, Menu } from 'antd';

import AvatarDropDown from 'src/components/AvatarDropDown';
import Footer from 'src/components/Layout/Footer';
import Header from 'src/components/Layout/Header';
import Sidebar from 'src/components/Layout/Sidebar';

import CookieAlert from 'src/components/CookieAlert';

import { MenuOutlined, ShoppingCartOutlined } from '@ant-design/icons';
import LoginModal from 'src/containers/Auth/LoginModal';
import { LoginService } from 'src/service/LoginService';
import BreadcrumbDn from '../BreadcrumbDn';
import styles from './style.module.less';

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


	const [visible, setVisible] = useState(false);

	const showDrawer = () => {
		setVisible(true);
	};

	const closeDrawer = () => {
		setVisible(false);
	};


	return (
		<>
			<Layout
				style={{
					minHeight: '100vh',
				}}
				className={styles.root}
			>
				<Header
					style={{
						left: 0,
					}}
				>
					<Link href="/" className={styles.headerLeft}>
						<div className={styles.logoCenter}>
							<Image
								src="/images/sorte.png"
								alt="Logo"
								width={55}
								height={35}
							/>
							<span>Loteria da Sorte</span>
						</div>
					</Link>

					<Sidebar mode="horizontal" className={styles.menuDesktop} />

					{loginService.autenticado() ?
						<div className={styles.headerRight}>
							<AvatarDropDown />
							<Link href="/meu-carrinho" passHref>
								<Button icon={<ShoppingCartOutlined />} shape="round" style={{ marginLeft: 10 }} />
							</Link>

						</div>
						:
						<div className={styles.headerRight}>
							<Button onClick={showLoginModal} shape="round">Entrar</Button>

							<Link href="/meu-carrinho" passHref>
								<Button icon={<ShoppingCartOutlined />} shape="round" style={{ marginLeft: 10 }} />
							</Link>
						</div>
					}


					{/* Button to open drawer on smaller screens */}
					<Button
						className={styles.menuMobileBtn}
						type="primary"
						icon={<MenuOutlined />}
						onClick={showDrawer}
					/>

					{/* Drawer for mobile screens */}
					<Drawer
						title="Menu"
						placement="right"
						onClose={closeDrawer}
						visible={visible}
					>
						<Sidebar mode="vertical" className={styles.menuMobile} />

					</Drawer>
				</Header>

				<Content
					style={{
						padding: '0 230px',
					}}
				>

					<BreadcrumbDn />

					<Layout className={styles.siteLayout}>

						{mobiShow && broken && <div className={styles.overlay} onClick={() => setMobiShow(false)} />}

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
