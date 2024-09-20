import React, { useState } from "react";
// import PropTypes from 'prop-types';

import { CreditCardOutlined, LockOutlined, MailOutlined, PhoneFilled, PhoneOutlined, UserOutlined } from "@ant-design/icons";
import { Button, Form, Input, message, Modal } from "antd";
import { useForm } from "antd/lib/form/Form";
import Logo from "src/components/Layout/Logo";
import { AccountPublicService } from "src/service/AccountPublicService";
import { LoginService } from "src/service/LoginService";
import classes from "./style.module.less";

import { useDispatch } from "react-redux";
import { useRouter } from "next/router";

const LoginModal = ({ visible, onClose }) => {
	const router = useRouter();
	const dispatch = useDispatch();

	const [loading, setLoading] = React.useState(false);

	const [loginForm] = useForm();
	const [newAccountForm] = useForm();

	const loginService = new LoginService();
	const accountPbService = new AccountPublicService();

	const [enabledCreateNewAccount, setEnabledCreateNewAccount] =
		useState(false);
	const [enabledForgotPasswordAccount, setEnabledForgotPasswordAccount] =
		useState(false);

	const doLogin = () => {
		setLoading(true);

		let request = {
			username: loginForm.getFieldValue("cpf"),
			password: loginForm.getFieldValue("password"),
		};

		loginForm.resetFields();
		loginService.doLogin(request, dispatch, router);
		setLoading(false);
		onClose();
	};

	const createAccount = () => {
		let request = {
			password: newAccountForm.getFieldValue("password"),
			name: newAccountForm.getFieldValue("name"),
			cpf: newAccountForm.getFieldValue("cpf"),
			contacts: [
				{
					type: "EMAIL",
					value: newAccountForm.getFieldValue("email"),
				},
				{
					type: "MOBILE_PHONE	",
					value: newAccountForm.getFieldValue("phone"),
				},
			],
		};

		accountPbService
			.postCreateAccountPb(request)
			.then((data) => {
				console.log(data);

				let loginRequest = {
					username: request?.cpf,
					password: request?.password,
				};

				newAccountForm.resetFields();
				loginForm.resetFields();
				loginService.doLogin(loginRequest, dispatch, router);
				onClose();
			})
			.catch((error) => {
				const errorMessage = error.response?.data?.message || "Erro desconhecido";
				console.error(error);
				showMessageError(errorMessage);
			});
	};

	const showMessageSuccess = (success) => {
		message.success(success);
	};

	const showMessageError = (erro) => {
		message.error(erro);
	};

	const showCreateNewAccount = () => {
		loginForm.resetFields();
		newAccountForm.resetFields();
		setEnabledCreateNewAccount(true);
		setEnabledForgotPasswordAccount(false);
	};

	const showForgotPasswordAccount = () => {
		loginForm.resetFields();
		newAccountForm.resetFields();
		setEnabledCreateNewAccount(false);
		setEnabledForgotPasswordAccount(true);
	};

	const showLogin = () => {
		loginForm.resetFields();
		newAccountForm.resetFields();
		setEnabledCreateNewAccount(false);
		setEnabledForgotPasswordAccount(false);
	};

	const closeModalIn = () => {
		loginForm.resetFields();
		newAccountForm.resetFields();
		setEnabledCreateNewAccount(false);
		setEnabledForgotPasswordAccount(false);
		onClose();
	};

	return (
		<Modal visible={visible} onCancel={() => closeModalIn()} footer={false}>
			{!enabledCreateNewAccount && !enabledForgotPasswordAccount && (
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
						margin: "0 auto 40px",
						borderRadius: 4,
						background: "#fff",
					}}
					size="large"
				>
					<div className="text-center mb-5">
						<Logo width={150} height={150} />
					</div>
					<Form.Item
						name="cpf"
						rules={[
							{
								required: true,
								message: "Digite seu CPF",
							},
						]}
					>
						<Input
							prefix={
								<UserOutlined className="site-form-item-icon" />
							}
							placeholder="CPF"
						/>
					</Form.Item>

					<Form.Item
						name="password"
						rules={[
							{
								required: true,
								message: "Digite sua Senha",
							},
						]}
					>
						<Input.Password
							prefix={
								<LockOutlined className="site-form-item-icon" />
							}
							type="password"
							placeholder="Senha"
						/>
					</Form.Item>

					<Form.Item>
						<Button
							type="primary"
							block
							htmlType="submit"
							className="login-form-button"
							loading={loading}
						>
							Entrar
						</Button>
					</Form.Item>

					<Form.Item>
						<div className="text-center">
							<Button onClick={() => showForgotPasswordAccount()}>
								Esqueci senha
							</Button>
						</div>
					</Form.Item>

					<div className="mt-5" style={{ textAlign: "center" }}>
						<Button
							className={classes.myLinkButton}
							onClick={() => showCreateNewAccount()}
						>
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
						margin: "0 auto 40px",
						borderRadius: 4,
						background: "#fff",
					}}
					size="large"
				>
					<div style={{ textAlign: "center" }}>
						<h3>Criar conta</h3>
					</div>

					<Form.Item
						name="name"
						rules={[
							{
								required: true,
								message: "Digite seu Nome Completo",
							},
						]}
					>
						<Input
							maxLength={250}
							prefix={
								<UserOutlined className="site-form-item-icon" />
							}
							placeholder="Nome Completo"
						/>
					</Form.Item>

					<Form.Item
						name="cpf"
						rules={[
							{
								required: true,
								message: "Digite seu CPF.",
							},
						]}
					>
						<Input
							maxLength={14}
							prefix={
								<CreditCardOutlined className="site-form-item-icon" />
							}
							placeholder="CPF"
						/>
					</Form.Item>

					<Form.Item
						name="email"
						rules={[
							{
								type: "email",
								message: "Não é um e-mail válido",
							},
							{
								required: true,
								message: "Digite seu E-mail",
							},
						]}
					>
						<Input
							maxLength={200}
							prefix={
								<MailOutlined className="site-form-item-icon" />
							}
							placeholder="E-mail"
						/>
					</Form.Item>

					<Form.Item
						name="phone"
						rules={[
							{
								required: true,
								message: "Digite seu Telefone",
							},
						]}
					>
						<Input
							maxLength={200}
							prefix={
								<PhoneOutlined className="site-form-item-icon" />
							}
							placeholder="Telefone"
						/>
					</Form.Item>

					<Form.Item
						name="password"
						rules={[
							{
								required: true,
								message: "Digite sua Senha",
							},
						]}
					>
						<Input.Password
							prefix={
								<LockOutlined className="site-form-item-icon" />
							}
							type="password"
							placeholder="Senha"
						/>
					</Form.Item>

					<Form.Item
						name="rePassword"
						rules={[
							{
								required: true,
								message: "Repita sua Senha",
							},
						]}
					>
						<Input.Password
							prefix={
								<LockOutlined className="site-form-item-icon" />
							}
							type="password"
							placeholder="Repita a Senha"
						/>
					</Form.Item>

					<Button
						type="primary"
						block
						htmlType="submit"
						className="login-form-button"
						loading={loading}
					>
						Criar
					</Button>

					<div className="mt-5" style={{ textAlign: "center" }}>
						<Button
							className={classes.myLinkButton}
							onClick={() => showLogin()}
						>
							Fazer Login
						</Button>
					</div>
				</Form>
			)}

			{enabledForgotPasswordAccount && (
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
						margin: "0 auto 40px",
						borderRadius: 4,
						background: "#fff",
					}}
					size="large"
				>
					<div style={{ textAlign: "center" }}>
						<h3>
							Insira seu e-mail para continuar com o processo da
							troca de sua senha.
						</h3>
					</div>

					<Form.Item
						name="contact"
						rules={[
							{
								type: "email",
								message: "Não é um e-mail válido",
							},
							{
								required: true,
								message: "Digite seu E-mail",
							},
						]}
					>
						<Input
							maxLength={200}
							prefix={
								<UserOutlined className="site-form-item-icon" />
							}
							placeholder="E-mail"
						/>
					</Form.Item>

					<Button
						type="primary"
						block
						htmlType="submit"
						className="login-form-button"
						loading={loading}
					>
						Criar
					</Button>

					<div className="mt-5" style={{ textAlign: "center" }}>
						<Button
							className={classes.myLinkButton}
							onClick={() => showLogin()}
						>
							Fazer Login
						</Button>
					</div>
				</Form>
			)}
		</Modal>
	);
};

export default LoginModal;
