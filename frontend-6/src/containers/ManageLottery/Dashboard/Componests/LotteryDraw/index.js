"use client";

import PropTypes from "prop-types";

import { Tabs, Card, Row, Col, Typography, Tag, Table, Spin } from "antd";
import { CheckCircleOutlined, CloseCircleOutlined } from "@ant-design/icons";

import { useForm } from "antd/lib/form/Form";
import dayjs from "dayjs";
import { withRouter } from "next/router";
import { useEffect, useState } from "react";
import { LotteryDrawService } from "src/service/LotteryDrawService";

const propTypes = {
	router: PropTypes.object.isRequired,
};

const defaultProps = {
	router: {},
};

const { Title, Text } = Typography;

const GameDashboard = (props) => {
	const { router, userList = [] } = props;

	const [gameForm] = useForm();

	const lotteryDrawService = new LotteryDrawService();

	const [games, setGames] = useState([]);
	const [loading, setLoading] = useState(true);

	const lotteryDrawSearch = () => {
		setLoading(true);
		lotteryDrawService
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

	const DrawNumbers = ({ numbers }) => (
		<Row gutter={[8, 8]} justify="center">
			{numbers.map((number, index) => (
				<Col key={index}>
					<div
						style={{
							width: 40,
							height: 40,
							borderRadius: "50%",
							backgroundColor: "#1890ff",
							color: "white",
							display: "flex",
							justifyContent: "center",
							alignItems: "center",
							fontSize: "18px",
							fontWeight: "bold",
						}}
					>
						{number}
					</div>
				</Col>
			))}
		</Row>
	);

	const PrizeBreakdownTable = ({ prizeBreakdown }) => {
		const columns = [
			{
				title: "Acertos",
				dataIndex: "prizeTierDescription",
				key: "prizeTierDescription",
			},
			{
				title: "Ganhadores",
				dataIndex: "numberOfWinners",
				key: "numberOfWinners",
			},
			{
				title: "Prêmio",
				dataIndex: "prizeAmount",
				key: "prizeAmount",
				render: (text) => `R$ ${parseFloat(text).toFixed(2)}`,
			},
		];

		return (
			<Table
				dataSource={prizeBreakdown}
				columns={columns}
				pagination={false}
			/>
		);
	};

	useEffect(() => {
		lotteryDrawSearch();
	}, []);

	return (
		<Spin spinning={loading} tip="carregando...">
			<Tabs defaultActiveKey="1">
				{games?.map((gameData) => (
					<Tabs.TabPane tab={gameData.gameType} key="1">
						<Card>
							<Row gutter={[16, 16]}>
								<Col xs={24} md={12}>
									<Title level={2}>
										{gameData.gameType} - Concurso{" "}
										{gameData.number}
									</Title>
									<Text>
										Data do Sorteio: {gameData.drawDate}
									</Text>
									<br />
									<Text>
										Próximo Sorteio: {gameData.nextDrawDate}
									</Text>
									<br />
									<Text>
										Local: {gameData.drawLocation},{" "}
										{gameData.drawCityState}
									</Text>
									<br />
									<Tag
										color={
											gameData.accumulated
												? "green"
												: "red"
										}
									>
										{gameData.accumulated ? (
											<CheckCircleOutlined />
										) : (
											<CloseCircleOutlined />
										)}
										{gameData.accumulated
											? " Acumulado"
											: " Não Acumulado"}
									</Tag>
								</Col>
								<Col xs={24} md={12}>
									<Title level={3}>Números Sorteados</Title>
									<DrawNumbers
										numbers={gameData.drawNumbers}
									/>
								</Col>
							</Row>
							<Row
								gutter={[16, 16]}
								style={{ marginTop: "20px" }}
							>
								<Col span={24}>
									<Title level={3}>Premiação</Title>
									<PrizeBreakdownTable
										prizeBreakdown={
											gameData.prizeBreakdownList
										}
									/>
								</Col>
							</Row>
							<Row
								gutter={[16, 16]}
								style={{ marginTop: "20px" }}
							>
								<Col xs={24} md={12}>
									<Card title="Informações Financeiras">
										<p>
											Arrecadação Total: R${" "}
											{parseFloat(
												gameData.collectedAmount
											).toFixed(2)}
										</p>
										<p>
											Próximo Prêmio Estimado: R${" "}
											{parseFloat(
												gameData.estimatedNextDrawAmount
											).toFixed(2)}
										</p>
										<p>
											Prêmio Total (7 acertos): R${" "}
											{parseFloat(
												gameData.totalPrizeAmountTierOne
											).toFixed(2)}
										</p>
									</Card>
								</Col>
							</Row>
						</Card>
					</Tabs.TabPane>
				))}
			</Tabs>
		</Spin>
	);
};

GameDashboard.propTypes = propTypes;

GameDashboard.defaultProps = defaultProps;

export default withRouter(GameDashboard);
