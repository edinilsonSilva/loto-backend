"use client";

import PropTypes from "prop-types";

import { Button, Card, Form, Input, message } from "antd";
import { useForm } from "antd/lib/form/Form";
import { withRouter } from "next/router";
import { PoolService } from "src/service/PoolService";
import styles from "./style.module.less";

const propTypes = {
	router: PropTypes.object.isRequired,
};

const defaultProps = {
	router: {},
};

const MyAccount = (props) => {
	const [poolForm] = useForm();

	const poolService = new PoolService();

	const createPool = () => {
		let request = {
			name: poolForm.getFieldValue("name"),
			entryFee: poolForm.getFieldValue("entryFee"),
			contestId: poolForm.getFieldValue("contestId"),
		};

		poolService
			.postCreatePool(request)
			.then((data) => {
				message.success(data?.message);
				poolForm.resetFields();
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
				name="poolForm"
				form={poolForm}
				className="login-form"
				initialValues={{
					remember: true,
				}}
				onFinish={createPool}
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
					<h3>Crie aqui um novo bolão</h3>
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
					<Input maxLength={250} placeholder="Nome do bolão" />
				</Form.Item>

				<Form.Item
					name="entryFee"
					rules={[
						{
							required: true,
							message: "Este campo é obrigatório",
						},
					]}
				>
					<Input
						maxLength={200}
						placeholder="Valor da entrada"
					/>
				</Form.Item>

				<Form.Item
					name="contestId"
					rules={[
						{
							required: true,
							message: "Este campo é obrigatório",
						},
					]}
				>
					<Input
						maxLength={200}
						placeholder="Concurso ID"
					/>
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
