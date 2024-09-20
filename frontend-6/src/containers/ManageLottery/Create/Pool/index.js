"use client";

import { useEffect, useState } from "react";
import PropTypes from "prop-types";

import { Button, Card, Form, Input, InputNumber, message, Select } from "antd";
import { useForm } from "antd/lib/form/Form";
import { withRouter } from "next/router";
import { PoolService } from "src/service/PoolService";
import { LotteryDrawService } from "src/service/LotteryDrawService";
import styles from "./style.module.less";

const { Option } = Select;

const propTypes = {
	router: PropTypes.object.isRequired,
};

const defaultProps = {
	router: {},
};

const MyAccount = (props) => {
	const [lotteryDraws, setLotteryDraws] = useState([]);
	const [loading, setLoading] = useState(true);

	const [poolForm] = useForm();

	const poolService = new PoolService();
	const lotteryDrawService = new LotteryDrawService();

	const createPool = (values) => {
		poolService
			.postCreatePool(values)
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

	const findAllReduced01 = () => {
		lotteryDrawService
			.findAllReduced01()
			.then((data) => {
				setLotteryDraws(data?.content);
			})
			.catch((error) => {
				const errorMessage =
					error.response?.data?.message || "Erro desconhecido";
				console.error(error);
				message.error(errorMessage);
			})
			.finally(() => {
				setLoading(false);
			});
	};

	useEffect(() => {
		findAllReduced01();
	}, []);

	const formatCurrency = (value) => {
		if (!value) return "R$0,00";
		return new Intl.NumberFormat("pt-BR", {
			style: "currency",
			currency: "BRL",
			minimumFractionDigits: 2,
			maximumFractionDigits: 2,
		}).format(value);
	};

	const parseCurrency = (value) => {
		return value.replace(/\D/g, "") / 100;
	};

	const handleKeyPress = (event) => {
		const charCode = event.which ? event.which : event.keyCode;
		if (
			(charCode < 48 || charCode > 57) &&
			charCode !== 44 &&
			charCode !== 46 &&
			charCode !== 8 &&
			!(charCode >= 37 && charCode <= 40)
		) {
			event.preventDefault();
		}
	};

	const handleBeforeInput = (event) => {
		const invalidChars = /[^0-9,]/g;
		if (invalidChars.test(event.data)) {
			event.preventDefault();
		}
	};

	return (
		<Card className={styles.card}>
			<Form
				form={poolForm}
				layout="vertical"
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

				{/* Lottery Draw ID */}
				<Form.Item
					label="Loteria"
					name="lotteryDrawId"
					rules={[
						{
							required: true,
							message: "Campo obrigatório",
						},
					]}
				>
					<Select placeholder="Selecione uma loteria">
						{lotteryDraws.map((draw) => (
							<Option key={draw.id} value={draw.id}>
								{draw.game} | concurso: {draw.number}
							</Option>
						))}
					</Select>
				</Form.Item>

				{/* Entry Fee */}
				<Form.Item
					label="Valor"
					name="entryFee"
					rules={[
						{
							required: true,
							message: "Campo obrigatório",
						},
					]}
				>
					<InputNumber
						onKeyPress={handleKeyPress}
						onBeforeInput={handleBeforeInput}
						formatter={(value) => formatCurrency(value)}
						parser={(value) => parseCurrency(value)}
						style={{ width: "100%" }}
					/>
				</Form.Item>

				{/* Total Shares */}
				<Form.Item
					label="Total de cotas"
					name="totalShares"
					rules={[
						{
							required: true,
							message: "Campo obrigatório",
						},
					]}
				>
					<InputNumber
						min={1}
						style={{ width: "100%" }}
						placeholder="Digite a quantidade de cotas disponíveis"
					/>
				</Form.Item>

				{/* Probability */}
				<Form.Item
					label="Probabilidade"
					name="probability"
					rules={[
						{
							required: true,
							message: "Campo obrigatório",
						},
					]}
				>
					<Select placeholder="Selecione uma probabilidade">
						<Option value="GOOD">Bom</Option>
						<Option value="VERY_GOOD">Muito Bom</Option>
						<Option value="HIGH">Alta</Option>
						<Option value="VERY_HIGH">Muito Alta</Option>
					</Select>
				</Form.Item>

				{/* Botão de Envio */}
				<Form.Item>
					<Button type="primary" htmlType="submit">
						Cadastrar
					</Button>
				</Form.Item>
			</Form>

			{/* <Form
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
			</Form> */}
		</Card>
	);
};

MyAccount.propTypes = propTypes;

MyAccount.defaultProps = defaultProps;

export default withRouter(MyAccount);
