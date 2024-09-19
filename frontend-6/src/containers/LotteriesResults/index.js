import PropTypes from "prop-types";

import { withRouter } from "next/router";
import { Tabs, Typography, Space, Card } from "antd";
import { format } from "date-fns";
import { LotteryDrawPublicService } from "src/service/LotteryDrawPublicService";
import { useEffect, useState } from "react";

const { Title, Text } = Typography;

const propTypes = {
	router: PropTypes.object.isRequired,
};

const defaultProps = {
	router: {},
};

const formatCurrency = (value) => {
	return new Intl.NumberFormat("pt-BR", {
		style: "currency",
		currency: "BRL",
	}).format(parseFloat(value));
};

const NumberCircle = ({ number }) => (
	<div
		style={{
			width: "40px",
			height: "40px",
			borderRadius: "50%",
			backgroundColor: "#f0f0f0",
			display: "flex",
			justifyContent: "center",
			alignItems: "center",
			margin: "0 5px",
			fontWeight: "bold",
		}}
	>
		{number}
	</div>
);

const GameTab = ({ game }) => (
	<Space direction="vertical" size="large" style={{ width: "100%" }}>
		<Title level={3}>{game.gameType.replace("_", " ")}</Title>
		<Space wrap>
			{game.drawNumbers.map((number, index) => (
				<NumberCircle key={index} number={number} />
			))}
		</Space>
		<Space direction="vertical">
			<Text>Concurso: {game.number}</Text>
			<Text>
				Data do Sorteio: {format(new Date(game.drawDate), "dd/MM/yyyy")}
			</Text>
			{game.nextDrawDate && (
				<Text>
					Próximo Sorteio:{" "}
					{format(new Date(game.nextDrawDate), "dd/MM/yyyy")}
				</Text>
			)}
			<Text>Acumulado: {game.accumulated ? "Sim" : "Não"}</Text>
			<Text>
				Arrecadação Total: {formatCurrency(game.collectedAmount)}
			</Text>
			<Text>
				Estimativa Próximo Prêmio:{" "}
				{formatCurrency(game.estimatedNextDrawAmount)}
			</Text>
		</Space>
	</Space>
);

const LotteriesResults = (props) => {
	const { router, userList = [] } = props;

	const lotteryDrawPublicService = new LotteryDrawPublicService();

	const [lotteryDraws, setLotteryDraws] = useState([]);

	const { Title, Text } = Typography;

	const lotteryDrawSearch = () => {
		lotteryDrawPublicService
			.getSearch()
			.then((data) => {
				setLotteryDraws(data?.content);
			})
			.catch((error) => {
				const errorMessage =
					error.response?.data?.message || "Erro desconhecido";
				console.error(error);
				message.error(errorMessage);
			});
	};

	useEffect(() => {
		lotteryDrawSearch();
	}, []);

	return (
		<Card className="row">
			<Tabs
				defaultActiveKey="1"
				tabPosition="top"
				style={{ height: "100vh", padding: "20px", width: "100%" }}
				items={lotteryDraws.map((game) => ({
					key: game.id.toString(),
					label: game.gameType.replace("_", " "),
					children: (
						<div
							style={{
								display: "flex",
								justifyContent: "center",
								alignItems: "center",
								height: "100%",
							}}
						>
							<GameTab game={game} />
						</div>
					),
				}))}
			/>
		</Card>
	);
};

LotteriesResults.propTypes = propTypes;

LotteriesResults.defaultProps = defaultProps;

export default withRouter(LotteriesResults);
