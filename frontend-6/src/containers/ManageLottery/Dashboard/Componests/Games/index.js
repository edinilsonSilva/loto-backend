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
	Table
} from "antd";
import { useForm } from "antd/lib/form/Form";
import dayjs from "dayjs";
import { withRouter } from "next/router";
import { useEffect, useState } from "react";
import { GameService } from "src/service/GameService";

const propTypes = {
	router: PropTypes.object.isRequired,
};

const defaultProps = {
	router: {},
};

const GameDashboard = (props) => {
	const { router, userList = [] } = props;

	const [gameForm] = useForm();

	const gameService = new GameService();

	const [games, setGames] = useState([]);
	const [loading, setLoading] = useState(true);

	const gameSearch = () => {
		setLoading(true);
		gameService
			.getSearch()
			.then((data) => {
				setGames(data?.content);
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
		gameSearch();
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
			title: "Nome",
			dataIndex: "name",
			key: "name",
		},
		{
			title: "Número máximo",
			dataIndex: "maxNumberValue",
			key: "maxNumberValue",
		},
		{
			title: "Concursos",
			dataIndex: "contests",
			key: "contests",
			render: (contests) => <div>{contests?.length}</div>,
		},
	];

	return (
		<Spin spinning={loading} tip="carregando...">
			<Form
				name="gameForm"
				form={gameForm}
				className="login-form"
				initialValues={{
					remember: true,
				}}
				onFinish={gameSearch}
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
									placeholder="Nome do Jogo"
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
													"/cadastrar-jogo"
											)
										}
									>
										Cadastrar novo jogo
									</Button>
								</Space>
							</Form.Item>
						</Col>
					</Space>
				</Row>
			</Form>
			<Table dataSource={games} columns={columns} />;
		</Spin>
	);
};

GameDashboard.propTypes = propTypes;

GameDashboard.defaultProps = defaultProps;

export default withRouter(GameDashboard);
