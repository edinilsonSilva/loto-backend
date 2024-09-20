"use client";

import PropTypes from "prop-types";

import { Card } from "antd";
import { useForm } from "antd/lib/form/Form";
import { withRouter } from "next/router";
import { useState } from "react";
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

	const [activeTabKey, setActiveTabKey] = useState("contests");

	const onTabChange = (key) => {
		setActiveTabKey(key);
	};

	const tabList = [
		{
			key: "contests",
			tab: "Detalhes dos jogos",
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
