import PropTypes from 'prop-types';

import { withRouter } from 'next/router';
import { Card, Col, Row } from 'antd';
import styles from "./style.module.less"
import { UserOutlined } from '@ant-design/icons';
import { useSelector } from 'react-redux';

const propTypes = {
	router: PropTypes.object.isRequired,
};

const defaultProps = {
	router: {},
};

const MyAccount = (props) => {

	const account = useSelector((state) => state.account);

	const { router, userList = [] } = props;

	return (
		<Card className={styles.card}>
			<span className={styles.cardTitle}>Minha Conta</span>

			<div className={styles.infoaccount} >
				<Row>
					<Col span={24}>
						<span className={styles.nameAccount}>{JSON.stringify(account)}</span>
					</Col>

					<Row style={{ width: '100%', padding: '15px', background: 'white', color: 'black'}}>
						<Col span={8} className={styles.mywallet}>
							<span>Minha Carteira</span>
						</Col>

						<Col span={15} className={styles.valuemoney}>
							<span>R$ 200</span>
						</Col>
					</Row>
				</Row>
			</div>

			<div
				onClick={() => alert("Meus dados clicado!")}
				className={styles.buttonDiv}
			>
				<Row>
					<Col span={4} className={styles.icon}>
						<UserOutlined style={{ fontSize: 24 }} />
					</Col>

					<Col span={20}>
						<span className={styles.title}>Meus dados</span>
						<span className={styles.subtitle}>Atualize aqui seu cadastro</span>
					</Col>
				</Row>
			</div>

			<div
				onClick={() => alert("Meus pedidos clicado!")}
				className={styles.buttonDiv}
			>
				<Row>
					<Col span={4} className={styles.icon}>
						<UserOutlined style={{ fontSize: 24 }} />
					</Col>

					<Col span={20}>
						<span className={styles.title}>Meus pedidos</span>
						<span className={styles.subtitle}>Acompanhe aqui todos os seus pedidos</span>
					</Col>
				</Row>
			</div>

		</Card>
	);
};

MyAccount.propTypes = propTypes;

MyAccount.defaultProps = defaultProps;

export default withRouter(MyAccount);
