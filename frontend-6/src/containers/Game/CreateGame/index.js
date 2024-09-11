"use client";

import PropTypes from "prop-types";

import {
	PhoneOutlined,
	UserOutlined
} from "@ant-design/icons";
import { Button, Card, Form, Input, message } from "antd";
import { useForm } from "antd/lib/form/Form";
import { withRouter } from "next/router";
import { GameService } from "src/service/GameService";
import styles from "./style.module.less";

const propTypes = {
	router: PropTypes.object.isRequired,
};

const defaultProps = {
	router: {},
};

const MyAccount = (props) => {
	const [gameForm] = useForm();

	const gameService = new GameService();

	const createGame = () => {
		let request = {
			name: gameForm.getFieldValue("name"),
			totalAmount: gameForm.getFieldValue("totalAmount"),
			minNumber: gameForm.getFieldValue("minNumber"),
			maxNumber: gameForm.getFieldValue("maxNumber"),
			maxNumberValue: gameForm.getFieldValue("maxNumberValue"),
		};

		gameService
			.postCreateGame(request)
			.then((data) => {

				message.success(data?.message);
				gameForm.resetFields();
			})
			.catch((error) => {
				const errorMessage =
					error.response?.data?.message || "Erro desconhecido";
				console.error(error);
				message.error(errorMessage);
			});
	};

	return (
		<Card className={styles.card}>
			<span className={styles.cardTitle}>Minha Conta</span>

			<Form
				name="gameForm"
				form={gameForm}
				className="login-form"
				initialValues={{
					remember: true,
				}}
				onFinish={createGame}
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
					<h3>Dados do Jogo</h3>
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
						placeholder="Nome do Jogo"
					/>
				</Form.Item>

				<Form.Item
					name="totalAmount"
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
						placeholder="Valor total"
					/>
				</Form.Item>

				<Form.Item
					name="minNumber"
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
						placeholder="Quantidade minima de escolhas"
					/>
				</Form.Item>

				<Form.Item
					name="maxNumber"
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
						placeholder="Quantidade máxima de escolhas"
					/>
				</Form.Item>

				<Form.Item
					name="maxNumberValue"
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
						placeholder="Número máximo do jogo"
					/>
				</Form.Item>

				<Form.Item>
					<div className="text-center">
						<Button htmlType="submit">Atualizar dados</Button>
					</div>
				</Form.Item>
			</Form>
		</Card>
	);
};

MyAccount.propTypes = propTypes;

MyAccount.defaultProps = defaultProps;

export default withRouter(MyAccount);
