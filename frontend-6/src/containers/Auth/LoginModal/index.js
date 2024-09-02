import React, { useState } from 'react';
// import PropTypes from 'prop-types';

import { LockOutlined, UserOutlined } from '@ant-design/icons';
import { Button, Form, Input, message, Modal } from 'antd';
import { useForm } from 'antd/lib/form/Form';
import Link from 'next/link';
import Logo from 'src/components/Layout/Logo';
import { AccountPbService } from 'src/service/AccountPbService';
import { LoginService } from 'src/service/LoginService';
import classes from './style.module.less';

const LoginModal = ({ visible, onClose }) => {

	const [loading, setLoading] = React.useState(false);

	const [loginForm] = useForm();
	const [newAccountForm] = useForm();

	const loginService = new LoginService();
	const accountPbService = new AccountPbService();

	const [enabledCreateNewAccount, setEnabledCreateNewAccount] = useState(false);

	const doLogin = () => {

		setLoading(true);

		let request = {
			username: loginForm.getFieldValue("username"),
			password: loginForm.getFieldValue("password"),
		}

		loginService.doLogin(request);
		setLoading(false)
	}

	const createAccount = () => {

		let request = {
			"username": newAccountForm.getFieldValue("username"),
			"password": newAccountForm.getFieldValue("password"),
			"name": newAccountForm.getFieldValue("name"),
			"cpf": newAccountForm.getFieldValue("cpf"),
			"contacts": [
				{
					"type": "EMAIL",
					"value": newAccountForm.getFieldValue("contact")
				}
			]
		}

		accountPbService.postCreateAccountPb(request)
			.then(data => {
				console.log(data);

				let loginRequest = {
					username: request?.username,
					password: request?.password,
				}
		
				loginService.doLogin(loginRequest);
			})
            .catch(error => {
                const errorMessage = error.response?.data?.message || 'Erro desconhecido';
				console.error(error);
                showMessageError(errorMessage);
            });
	}
	
	const showMessageSuccess = (success) => {
        message.success(success);
    }

    const showMessageError = (erro) => {
        message.error(erro);
    }

	const showCreateNewAccount = () => {
		loginForm.resetFields();
		newAccountForm.resetFields();
		setEnabledCreateNewAccount(true);
	}

	const showLogin = () => {
		loginForm.resetFields();
		newAccountForm.resetFields();
		setEnabledCreateNewAccount(false);
	}

	const closeModalIn = () => {
		loginForm.resetFields();
		newAccountForm.resetFields();
		setEnabledCreateNewAccount(false);
		onClose();
	}

	return (
		<Modal
			visible={visible}
			onCancel={() => closeModalIn()}
			footer={false}
		>
			{!enabledCreateNewAccount && (
				<Form
					name="loginForm"
					form={loginForm}
					className="login-form"
					initialValues={{
						remember: true,
					}}
					onFinish={doLogin}
					style={{
						width: 350,
						padding: 20,
						margin: '0 auto 40px',
						borderRadius: 4,
						background: '#fff',
					}}
					size="large"
				>
					<div className="text-center mb-5">
						<Logo
							width={150}
							height={150}
						/>
					</div>
					<Form.Item
						name="username"
						rules={[
							{
								required: true,
								message: 'Digite seu Usuário',
							},
						]}
					>
						<Input prefix={<UserOutlined className="site-form-item-icon" />} placeholder="Username" />
					</Form.Item>

					<Form.Item
						name="password"
						rules={[
							{
								required: true,
								message: 'Digite sua Senha',
							},
						]}
					>
						<Input.Password
							prefix={<LockOutlined className="site-form-item-icon" />}
							type="password"
							placeholder="Password"
						/>
					</Form.Item>

					<Form.Item>
						<div className="text-center">
							<Link href="/forgot-password" className="login-form-forgot">
								Forgot password
							</Link>
						</div>
					</Form.Item>

					<Button type="primary" block htmlType="submit" className="login-form-button" loading={loading}>
						Entrar
					</Button>

					<div className="mt-5" style={{ textAlign: 'center' }}>
						<Button className={classes.myLinkButton} onClick={() => showCreateNewAccount()}>
							Criar conta
						</Button>
					</div>
				</Form>
			)}

			{enabledCreateNewAccount && (
				<Form
					name="newAccountForm"
					form={newAccountForm}
					className="login-form"
					initialValues={{
						remember: true,
					}}
					onFinish={createAccount}
					style={{
						width: 350,
						padding: 20,
						margin: '0 auto 40px',
						borderRadius: 4,
						background: '#fff',
					}}
					size="large"
				>
					<div style={{ textAlign: 'center' }}>
						<h3>Criar conta</h3>
					</div>

					<Form.Item
						name="username"
						rules={[
							{
								required: true,
								message: 'Digite seu Usuário',
							},
						]}
					>
						<Input maxLength={20} prefix={<UserOutlined className="site-form-item-icon" />} placeholder="Nome do Usuário" />
					</Form.Item>


					<Form.Item
						name="name"
						rules={[
							{
								required: true,
								message: 'Digite seu Nome Completo',
							},
						]}
					>
						<Input maxLength={250} prefix={<UserOutlined className="site-form-item-icon" />} placeholder="Nome Completo" />
					</Form.Item>


					<Form.Item
						name="cpf"
						rules={[
							{
								required: true,
								message: 'Digite seu CPF.',
							},
						]}
					>
						<Input maxLength={14} prefix={<UserOutlined className="site-form-item-icon" />} placeholder="CPF" />
					</Form.Item>

					<Form.Item
						name="contact"
						rules={[
							{
								type: 'email',
								message: 'Não é um e-mail válido',
							}, {
								required: true,
								message: 'Digite seu E-mail',
							},
						]}
					>
						<Input maxLength={200} prefix={<UserOutlined className="site-form-item-icon" />} placeholder="E-mail" />
					</Form.Item>

					<Form.Item
						name="password"
						rules={[
							{
								required: true,
								message: 'Digite sua Senha',
							},
						]}
					>
						<Input.Password
							prefix={<LockOutlined className="site-form-item-icon" />}
							type="password"
							placeholder="Senha"
						/>
					</Form.Item>

					<Form.Item
						name="rePassword"
						rules={[
							{
								required: true,
								message: 'Repita sua Senha',
							},
						]}
					>
						<Input.Password
							prefix={<LockOutlined className="site-form-item-icon" />}
							type="password"
							placeholder="Repita a Senha"
						/>
					</Form.Item>

					<Button type="primary" block htmlType="submit" className="login-form-button" loading={loading}>
						Criar
					</Button>

					<div className="mt-5" style={{ textAlign: 'center' }}>
						<Button className={classes.myLinkButton} onClick={() => showLogin()}>
							Fazer Login
						</Button>
					</div>


				</Form>
			)}

		</Modal >
	);
};

export default LoginModal;
