"use client";

import PropTypes from "prop-types";

import {
	Card,
	message
} from "antd";
import { useForm } from "antd/lib/form/Form";
import { withRouter } from "next/router";
import { useEffect, useState } from "react";
import { GameService } from "src/service/GameService";
import LotteryDraw from "./Componests/LotteryDraw";
import PoolDashboard from "./Componests/Pools";
import styles from "./style.module.less";

const propTypes = {
	router: PropTypes.object.isRequired,
};

const defaultProps = {
	router: {},
};

const ManageLotteryDashboard = (props) => {
	const { router, userList = [] } = props;

	const [gameForm] = useForm();

	const gameService = new GameService();

	const [games, setGames] = useState([]);
	const [activeTabKey, setActiveTabKey] = useState("games");

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

	const onTabChange = (key) => {
		setActiveTabKey(key);
	};

	const tabList = [
		{
			key: "contests",
			tab: "Concursos",
		},
		{
			key: "pools",
			tab: "Bolões",
		},
	];

	return (
		<Card
			title="Gerenciar Loterica"
			tabList={tabList}
			activeTabKey={activeTabKey}
			onTabChange={onTabChange}
			className={styles.card}
		>
			{activeTabKey === "contests" && <LotteryDraw />}

			{activeTabKey === "pools" && <PoolDashboard />}
		</Card>
	);
};

ManageLotteryDashboard.propTypes = propTypes;

ManageLotteryDashboard.defaultProps = defaultProps;

export default withRouter(ManageLotteryDashboard);
