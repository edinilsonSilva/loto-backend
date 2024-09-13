"use client";

import PropTypes from "prop-types";

import {
	Button,
	Col,
	Form,
	Input,
	message,
	Row,
	Space,
	Spin,
	Table,
} from "antd";
import { useForm } from "antd/lib/form/Form";
import { withRouter } from "next/router";
import { useEffect, useState } from "react";
import { ContestService } from "src/service/ContestService";
import dayjs from "dayjs";

const propTypes = {
	router: PropTypes.object.isRequired,
};

const defaultProps = {
	router: {},
};

const ContestDashboard = (props) => {
	const { router, userList = [] } = props;

	const [contestForm] = useForm();

	const contestService = new ContestService();

	const [contests, setContests] = useState([]);
	const [loading, setLoading] = useState(true);

	const contestSearch = () => {
		setLoading(true);
		contestService
			.getSearch()
			.then((data) => {
				setContests(data?.content);
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
		contestSearch();
	}, []);

	const columns = [
		{
			title: "Criado em",
			dataIndex: "createdAt",
			key: "createdAt",
			render: (createdAt) => (
				<span>{dayjs(createdAt).format("DD/MM/YYYY HH:mm")}</span>
			),
		},
		{
			title: "Número",
			dataIndex: "contestNumber",
			key: "contestNumber",
		},
		{
			title: "Data do concurso",
			dataIndex: "drawDate",
			key: "drawDate",
			render: (drawDate) => (
				<span>{dayjs(drawDate).format("DD/MM/YYYY")}</span>
			),
		},
		{
			title: "Valor Acumulado",
			dataIndex: "prizeAmount",
			key: "prizeAmount",
		},
		{
			title: "Jogo",
			dataIndex: "game",
			key: "game",
			render: (game) => <div>{game?.name}</div>,
		},
	];

	return (
		<Spin spinning={loading} tip="carregando...">
			<Form
				name="ContestForm"
				form={contestForm}
				className="login-form"
				initialValues={{
					remember: true,
				}}
				onFinish={contestSearch}
				style={{
					width: "100%",
					padding: 20,
					margin: "0 auto 40px",
					borderRadius: 4,
					background: "#fff",
				}}
				size="large"
			>
				<Row>
					<Space>
						<Col>
							<Form.Item name="name">
								<Input
									maxLength={250}
									placeholder="Número do concurso"
								/>
							</Form.Item>
						</Col>

						<Col>
							<Form.Item>
								<Space>
									<Button htmlType="submit">Pesquisar</Button>

									<Button
										onClick={() =>
											router.push(
												router.asPath +
													"/cadastrar-concurso"
											)
										}
									>
										Cadastrar novo concurso
									</Button>
								</Space>
							</Form.Item>
						</Col>
					</Space>
				</Row>
			</Form>
			<Table dataSource={contests} columns={columns} />;
		</Spin>
	);
};

ContestDashboard.propTypes = propTypes;

ContestDashboard.defaultProps = defaultProps;

export default withRouter(ContestDashboard);
