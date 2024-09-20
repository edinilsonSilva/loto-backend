import PropTypes from "prop-types";

import { withRouter } from "next/router";

import {
	Layout,
	Button,
	Pagination,
	Select,
	Checkbox,
	Radio,
	Card,
	Col,
	Divider,
	Input,
	Row,
	Spin,
} from "antd";
import { SearchOutlined, ShoppingCartOutlined } from "@ant-design/icons";
import styles from "./style.module.less";
import { useState } from "react";

const { Header, Content, Sider } = Layout;
const { Option } = Select;

const propTypes = {
	router: PropTypes.object.isRequired,
};

const defaultProps = {
	router: {},
};

const BettingPool = (props) => {
	const { router, userList = [] } = props;

	const [currentPage, setCurrentPage] = useState(1);
	const [loading, setLoading] = useState(false);

	// Exemplo de dados
	const lotteryGroups = [
		{
			gameType: "Mega-Sena",
			groupName: "MS-LEG-002",
			probability: "Boa",
			price: "R$ 20",
			date: "19/09/2024",
			concurso: "2776",
			status: "Premiada!",
		},
		// Mais grupos de bolão...
	];

	return (
		<Layout style={{ minHeight: "100vh" }}>
			{/* Filtros Laterais */}
			<Sider width={250} style={{ background: "#fff", padding: "20px" }}>
				<h3>Filtre sua sorte</h3>
				<Checkbox.Group style={{ width: "100%" }}>
					<Row>
						<Col span={24}>
							<Checkbox value="chance">+Chances</Checkbox>
						</Col>
						<Col span={24}>
							<Checkbox value="acumulada">Acumulada</Checkbox>
						</Col>
						<Col span={24}>
							<Checkbox value="premiada">Premiada</Checkbox>
						</Col>
					</Row>
				</Checkbox.Group>

				<h3>Preço</h3>
				<Radio.Group style={{ display: "block" }}>
					<Radio value={10}>Até R$ 10,00</Radio>
					<Radio value={20}>De R$ 10,01 a R$ 20,00</Radio>
					<Radio value={50}>De R$ 20,01 a R$ 50,00</Radio>
					<Radio value={100}>Acima de R$ 100,00</Radio>
				</Radio.Group>

				<h3>Lotterias</h3>
				<Checkbox.Group style={{ width: "100%" }}>
					<Row>
						<Col span={24}>
							<Checkbox value="mega-sena">Mega-Sena</Checkbox>
						</Col>
						<Col span={24}>
							<Checkbox value="quina">Quina</Checkbox>
						</Col>
						<Col span={24}>
							<Checkbox value="lotofacil">Lotofácil</Checkbox>
						</Col>
						{/* Adicione mais opções de loteria */}
					</Row>
				</Checkbox.Group>

				<Button type="primary" style={{ marginTop: "20px" }} block>
					Aplicar Filtros
				</Button>
				<Button style={{ marginTop: "10px" }} block>
					Limpar Filtros
				</Button>
			</Sider>

			{/* Conteúdo principal */}
			<Layout>
				<Header style={{ background: "#fff", padding: "20px" }}>
					<Input
						size="large"
						placeholder="Buscar por grupo de bolão"
						prefix={<SearchOutlined />}
						style={{ width: "50%", marginRight: "20px" }}
					/>
					<Select defaultValue="Ordenar" style={{ width: 150 }}>
						<Option value="recentes">Mais recentes</Option>
						<Option value="preco">Menor preço</Option>
					</Select>
				</Header>

				<Content style={{ padding: "20px" }}>
					<Row gutter={[16, 16]}>
						{lotteryGroups.map((group, index) => (
							<Col span={24} key={index}>
								<Card>
									<Row justify="space-between" align="middle">
										<Col>
											<h3>{group.gameType}</h3>
											<p>Grupo: {group.groupName}</p>
											<p>
												Probabilidade:{" "}
												{group.probability}
											</p>
										</Col>
										<Col>
											<p>Conc.: {group.concurso}</p>
											<p>Data: {group.date}</p>
											<p>Preço: {group.price}</p>
										</Col>
										<Col>
											<Button icon={<SearchOutlined />}>
												Ver detalhes
											</Button>
											<Button
												icon={<ShoppingCartOutlined />}
												style={{
													marginLeft: "10px",
												}}
											>
												{group.status}
											</Button>
										</Col>
									</Row>
								</Card>
							</Col>
						))}
					</Row>

					{/* Paginação */}
					<Pagination
						style={{
							marginTop: "20px",
							textAlign: "center",
						}}
						current={currentPage}
						onChange={(page) => setCurrentPage(page)}
						total={50}
					/>
				</Content>
			</Layout>
		</Layout>
	);
};

BettingPool.propTypes = propTypes;

BettingPool.defaultProps = defaultProps;

export default withRouter(BettingPool);
