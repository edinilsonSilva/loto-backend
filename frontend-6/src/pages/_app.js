/* eslint-disable react/prop-types */
import React from 'react';
// import App from 'next/app';
import Head from 'next/head';

import { useAsync } from 'react-use';

import { useRouter } from 'next/router';

import MainLayout from 'src/components/Layout/MainLayout';
import Loading from 'src/components/Loading';

import { Provider } from 'react-redux';
import store from '../redux/store';

//import 'antd/dist/antd.css';

require('src/styles/index.less');

const urlsIgnore = ['/forgot-password', '/login-first', '/login', '/sign-up', '/verify-email', '/reset-password'];

const MyApp = (props) => {

	const { Component, pageProps } = props;
	const [awaitLoading, setAwaitLoading] = React.useState(true);
	const router = useRouter();

	const Layout = Component.Layout || MainLayout;

	useAsync(async () => {
		// if (AuthStorage.loggedIn) {
		// 	try {
		// 		await dispatch(await actionGetUserAuth());
		// 	} catch (error) {
		// 		if ((error.status === 403 || error.status === 401) && error.code !== 'AUTHORIZATION_REQUIRED') {
		// 			AuthStorage.destroy();
		// 			dispatch({ type: 'LOGOUT_SUCCESS' });

		// 			if (router.pathname !== '/login') {
		// 				router.push('/login');
		// 			}
		// 		}
		// 	}
		// 	setAwaitLoading(false);
		// } else {
		// 	setAwaitLoading(false);
		// }

		setAwaitLoading(false);

	}, []);
	//	}, [AuthStorage.loggedIn]);

	return (
		<Provider store={store}>
			<Layout>
				<Head>
					<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no, height=device-height, user-scalable=0" />
				</Head>
				<Component {...pageProps} router={router} />
				<Loading fullScreen loading={awaitLoading} />
			</Layout>
		</Provider>
	);
};

export default MyApp;
