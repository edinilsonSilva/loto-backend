"use client";

import PropTypes from "prop-types";

import { Button, Card, Form, Input, message } from "antd";
import { useForm } from "antd/lib/form/Form";
import { withRouter } from "next/router";
import { ContestService } from "src/service/ContestService";
import styles from "./style.module.less";

const propTypes = {
	router: PropTypes.object.isRequired,
};

const defaultProps = {
	router: {},
};

const MyAccount = (props) => {
	const [contestForm] = useForm();

	const contestService = new ContestService();

	const createContest = () => {
		let request = {
			contestNumber: contestForm.getFieldValue("contestNumber"),
			prizeAmount: contestForm.getFieldValue("prizeAmount"),
			drawDate: contestForm.getFieldValue("drawDate"),
			gameId: contestForm.getFieldValue("gameId"),
		};

		contestService
			.postCreateContest(request)
			.then((data) => {
				message.success(data?.message);
				contestForm.resetFields();
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

			<Form
				name="contestForm"
				form={contestForm}
				className="login-form"
				initialValues={{
					remember: true,
				}}
				onFinish={createContest}
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
					<h3>Crie aqui um novo concurso</h3>
				</div>

				<Form.Item
					name="contestNumber"
					rules={[
						{
							required: true,
							message: "Este campo é obrigatório",
						},
					]}
				>
					<Input maxLength={250} placeholder="Número do concurso" />
				</Form.Item>

				<Form.Item
					name="prizeAmount"
					rules={[
						{
							required: true,
							message: "Este campo é obrigatório",
						},
					]}
				>
					<Input maxLength={200} placeholder="Valor acumulado" />
				</Form.Item>

				<Form.Item
					name="drawDate"
					rules={[
						{
							required: true,
							message: "Este campo é obrigatório",
						},
					]}
				>
					<Input maxLength={200} placeholder="Data do concurso" />
				</Form.Item>

				<Form.Item
					name="gameId"
					rules={[
						{
							required: true,
							message: "Este campo é obrigatório",
						},
					]}
				>
					<Input maxLength={200} placeholder="Jogo ID" />
				</Form.Item>

				<Form.Item>
					<div className="text-center">
						<Button htmlType="submit">Cadastrar</Button>
					</div>
				</Form.Item>
			</Form>
		</Card>
	);
};

MyAccount.propTypes = propTypes;

MyAccount.defaultProps = defaultProps;

export default withRouter(MyAccount);
