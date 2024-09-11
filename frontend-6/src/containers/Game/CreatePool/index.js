"use client";

import PropTypes from "prop-types";

import {
	CreditCardOutlined,
	LockOutlined,
	MailOutlined,
	PhoneOutlined,
	UserOutlined,
} from "@ant-design/icons";
import { Button, Card, Form, Input } from "antd";
import { useForm } from "antd/lib/form/Form";
import { withRouter } from "next/router";
import { LoginService } from "src/service/LoginService";
import styles from "./style.module.less";
import { useEffect } from "react";

const propTypes = {
	router: PropTypes.object.isRequired,
};

const defaultProps = {
	router: {},
};

const MyAccount = (props) => {
	const [accountForm] = useForm();
	const [accountPasswordForm] = useForm();

	const account = new LoginService().getAccount();

	const { router, userList = [] } = props;

	useEffect(() => {
		accountForm.setFieldsValue(account);
	}, [account]);

	const updateAccount = () => {};
	const updatePassword = () => {};

	return (
		<Card className={styles.card}>
			<span className={styles.cardTitle}>Minha Conta</span>

			<Form
				name="accountForm"
				form={accountForm}
				className="login-form"
				initialValues={{
					remember: true,
				}}
				onFinish={updateAccount}
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
					<h3>Dados da conta</h3>
				</div>

				<Form.Item
					name="name"
					rules={[
						{
							required: true,
							message: "Este campo é obrigatório",
						},
					]}
				>
					<Input
						maxLength={250}
						prefix={
							<UserOutlined className="site-form-item-icon" />
						}
						placeholder="Nome completo"
					/>
				</Form.Item>

				<Form.Item
					name="cpf"
					rules={[
						{
							required: true,
							message: "Este campo é obrigatório",
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
							message: "Este campo é obrigatório",
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
							message: "Este campo é obrigatório",
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

				<Form.Item>
					<div className="text-center">
						<Button htmlType="submit">Atualizar dados</Button>
					</div>
				</Form.Item>
			</Form>

			<Form
				name="accountPasswordForm"
				form={accountPasswordForm}
				className="login-form"
				initialValues={{
					remember: true,
				}}
				onFinish={updatePassword}
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
					<h3>Sua senha</h3>
				</div>

				<Form.Item
					name="password"
					rules={[
						{
							required: true,
							message: "Este campo é obrigatório",
						},
					]}
				>
					<Input.Password
						prefix={
							<LockOutlined className="site-form-item-icon" />
						}
						type="password"
						placeholder="Digite sua nova senha"
					/>
				</Form.Item>

				<Form.Item
					name="rePassword"
					rules={[
						{
							required: true,
							message: "Este campo é obrigatório",
						},
					]}
				>
					<Input.Password
						prefix={
							<LockOutlined className="site-form-item-icon" />
						}
						type="password"
						placeholder="Repita sua nova enha"
					/>
				</Form.Item>

				<Form.Item>
					<div className="text-center">
						<Button htmlType="submit">Atualizar senha</Button>
					</div>
				</Form.Item>
			</Form>
		</Card>
	);
};

MyAccount.propTypes = propTypes;

MyAccount.defaultProps = defaultProps;

export default withRouter(MyAccount);
