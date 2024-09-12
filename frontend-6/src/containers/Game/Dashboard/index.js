"use client";

import PropTypes from "prop-types";

import { PhoneOutlined, UserOutlined } from "@ant-design/icons";
import { Button, Card, Form, Input, message, Table } from "antd";
import { useForm } from "antd/lib/form/Form";
import { withRouter } from "next/router";
import { GameService } from "src/service/GameService";
import styles from "./style.module.less";
import { useEffect, useState } from "react";

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

	const gameSearch = () => {
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
			});
	};

	useEffect(() => {
		gameSearch();
	}, []);

	const columns = [
		{
			title: "Nome",
			dataIndex: "name",
			key: "name",
		},
		{
			title: "Cadastrado em",
			dataIndex: "createdAt",
			key: "createdAt",
		},
		{
			title: "Address",
			dataIndex: "maxNumberValue",
			key: "maxNumberValue",
		},
	];

	return (
		<Card
			title={"Todos os jogos"}
			className={styles.card}
			extra={
				<Button
					onClick={() => router.push(router.asPath + "/cadastrar-jogo")}
				>
					Cadastrar novo jogo
				</Button>
			}
			style={{ width: "100%" }}
		>
			<Form
				name="gameForm"
				form={gameForm}
				className="login-form"
				initialValues={{
					remember: true,
				}}
				onFinish={gameSearch}
				style={{
					width: 350,
					padding: 20,
					margin: "0 auto 40px",
					borderRadius: 4,
					background: "#fff",
				}}
				size="large"
			>
				<Form.Item name="name">
					<Input maxLength={250} placeholder="Nome do Jogo" />
				</Form.Item>

				<Form.Item>
					<div className="text-center">
						<Button htmlType="submit">Pesquisar</Button>
					</div>
				</Form.Item>
			</Form>
			<Table dataSource={games} columns={columns} />;
		</Card>
	);
};

GameDashboard.propTypes = propTypes;

GameDashboard.defaultProps = defaultProps;

export default withRouter(GameDashboard);
