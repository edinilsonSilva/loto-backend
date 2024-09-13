"use client";

import PropTypes from "prop-types";

import { SettingOutlined, SolutionOutlined, UserOutlined } from "@ant-design/icons";
import { Card, Col, Row } from "antd";
import { withRouter } from "next/router";
import { LoginService } from "src/service/LoginService";
import styles from "./style.module.less";

const propTypes = {
	router: PropTypes.object.isRequired,
};

const defaultProps = {
	router: {},
};

const validateCurrency = (value) => {
	if (value === "BRL") {
		return "R$ ";
	} else if (value === "USD") {
		return "U$ ";
	} else if (value === "EUR") {
		return "E$ ";
	}
};

const MyAccount = (props) => {
	const account = new LoginService().getAccount();

	const { router, userList = [] } = props;

	return (
		<Card className={styles.card}>
			<span className={styles.cardTitle}>Minha Conta</span>

			<div className={styles.infoaccount}>
				<Row>
					<Col span={24}>
						<span className={styles.nameAccount}>
							{account?.name}
						</span>
					</Col>

					<Row
						style={{
							width: "100%",
							padding: "15px",
							background: "white",
							color: "black",
						}}
					>
						<Col span={8} className={styles.mywallet}>
							<span>Minha(s) Carteira(s) </span>
						</Col>

						{account?.wallets?.map((w) => (
							<Col span={15} className={styles.valuemoney}>
								<span>
									{validateCurrency(w?.currency) + w.balance}
								</span>
							</Col>
						))}
					</Row>
				</Row>
			</div>

			<div
				onClick={() => router.push(router.asPath + "/meus-dados")}
				className={styles.buttonDiv}
			>
				<Row>
					<Col span={4} className={styles.icon}>
						<UserOutlined style={{ fontSize: 24 }} />
					</Col>

					<Col span={20}>
						<span className={styles.title}>Meus dados</span>
						<span className={styles.subtitle}>
							Atualize aqui seu cadastro
						</span>
					</Col>
				</Row>
			</div>

			<div
				onClick={() => alert("Meus pedidos clicado!")}
				className={styles.buttonDiv}
			>
				<Row>
					<Col span={4} className={styles.icon}>
						<SolutionOutlined style={{ fontSize: 24 }} />
					</Col>

					<Col span={20}>
						<span className={styles.title}>Meus pedidos</span>
						<span className={styles.subtitle}>
							Acompanhe aqui todos os seus pedidos
						</span>
					</Col>
				</Row>
			</div>

			<div
				onClick={() => router.push(router.asPath + "/gerenciar-loterica")}
				className={styles.buttonDiv}
			>
				<Row>
					<Col span={4} className={styles.icon}>
						<SettingOutlined style={{ fontSize: 24 }} />
					</Col>

					<Col span={20}>
						<span className={styles.title}>Gerenciar Lotérica</span>
						<span className={styles.subtitle}>
							Gerencie a lotérica - Jogos, Concursos e Bolões (PERFIL ADMIN)
						</span>
					</Col>
				</Row>
			</div>

			<div
				onClick={() => router.push(router.asPath + "/cadastrar-bolao")}
				className={styles.buttonDiv}
			>
				<Row>
					<Col span={4} className={styles.icon}>
						<UserOutlined style={{ fontSize: 24 }} />
					</Col>

					<Col span={20}>
						<span className={styles.title}>Novo bolão</span>
						<span className={styles.subtitle}>
							Cadastre um Bolao
						</span>
					</Col>
				</Row>
			</div>
		</Card>
	);
};

MyAccount.propTypes = propTypes;

MyAccount.defaultProps = defaultProps;

export default withRouter(MyAccount);
