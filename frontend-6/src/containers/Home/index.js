import {
	Badge,
	Button,
	Card,
	Carousel,
	Col,
	message,
	Row,
	Typography,
} from "antd";
import { useEffect, useState } from "react";
import { GameService } from "src/service/GameService";
import styles from "./style.module.less";

// import PropTypes from 'prop-types';

// import classes from './style.less';

const propTypes = {
	// classes: PropTypes.object.isRequired,
};

const defaultProps = {
	// classes: {},
};

const contentStyle = {
	margin: 0,
	height: "160px",
	color: "#fff",
	lineHeight: "160px",
	textAlign: "center",
	background: "#364d79",
};

const onChange = (currentSlide) => {
	console.log(currentSlide);
};

const Index = (props) => {
	// const { } = props;

	const gameService = new GameService();

	const [games, setGames] = useState([]);

	const { Title, Text } = Typography;

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

	return (
		<Card style={{ minHeight: "100vh" }}>
			<Carousel arrows infinite={false}>
				<div>
					<h3 style={contentStyle}>1</h3>
				</div>
				<div>
					<h3 style={contentStyle}>2</h3>
				</div>
				<div>
					<h3 style={contentStyle}>3</h3>
				</div>
				<div>
					<h3 style={contentStyle}>4</h3>
				</div>
			</Carousel>

			<br />

			<Row gutter={[16, 16]}>
				{games?.map((game) => (
					<Col span={8}>
						<Card className={styles.lotteryCard} bordered={false}>
							<div className={styles.lotteryHeader}>
								<Text className={styles.lotteryTitle}>
									{game?.name?.toUpperCase()}
								</Text>
								<Text className={styles.lotteryConcurso}>
									Concurso {game?.contests[0]?.contestNumber}
								</Text>
							</div>

							<Title className={styles.lotteryValue} level={1}>
								R$ {game?.contests[0]?.prizeAmount}
							</Title>
							<Badge
								count="Acumulou!"
								className={styles.lotteryBadge}
								style={{
									backgroundColor: "#FFC107",
									color: "#000",
								}}
							/>

							<div className={styles.lotteryDate}>
								<Text strong>
									{game?.contests[0]?.drawDate}
								</Text>
							</div>

							<div className={styles.lotteryButtons}>
								<Button
									type="default"
									size="large"
									className={styles.lotteryButton}
								>
									FAÇA SEU JOGO
								</Button>
								<Button
									type="primary"
									size="large"
									className={styles.lotteryButton}
								>
									VER BOLÕES
								</Button>
							</div>
						</Card>
					</Col>
				))}
			</Row>
		</Card>
	);
};

Index.propTypes = propTypes;

Index.defaultProps = defaultProps;

export default Index;
