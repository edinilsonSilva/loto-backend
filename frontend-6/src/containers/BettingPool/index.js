import PropTypes from 'prop-types';

import { withRouter } from 'next/router';
import { Card, Col, Divider, Input, Row } from 'antd';
import styles from './style.module.less';

const propTypes = {
	router: PropTypes.object.isRequired,
};

const defaultProps = {
	router: {},
};

const BettingPool = (props) => {

	const { router, userList = [] } = props;

	return (
		<Card className="row" style={{ width: '100%' }}>
			<span>Bolões</span>
			<Row style={{ width: '100%' }}>

				<Col span={6} style={{ paddingRight: '10px' }}>
					<p><Input /></p>
					<p><Input /></p>
					<p><Input /></p>
				</Col>

				<Col span={18}>
					<div className={styles.gameContainer}>
						<div className={styles.gameInfo}>
							<div className={styles.gameTitle}>Dia de Sorte</div>
							<div className={styles.gameDetails}>
								<span className={styles.group}>Grupo: ST-HAA-006</span>
								<span className={styles.probability}>
									<span className={styles.probabilityIcons}>
										<i className={styles.icon}></i>
										<i className={styles.icon}></i>
										<i className={styles.icon}></i>
										<i className={styles.iconInactive}></i>
									</span>
									<span>Muito Boa</span>
								</span>
							</div>
							<div className={styles.gameStatus}>
								<span className={styles.statusBadge}>Premiada!</span>
								<span className={styles.details}>Ver detalhes</span>
								<span className={styles.remaining}>
									restam <span className={styles.remainingCount}>2/25</span>
								</span>
							</div>
						</div>
						<div className={styles.gamePurchase}>
							<div className={styles.price}>R$10</div>
							<div className={styles.cartButton}>
								<i className={styles.cartIcon}></i>
							</div>
						</div>
					</div>

					<Divider dashed />

					<div className={styles.gameContainer}>
						<div className={styles.gameInfo}>
							<div className={styles.gameTitle}>Dia de Sorte</div>
							<div className={styles.gameDetails}>
								<span className={styles.group}>Grupo: ST-HAA-006</span>
								<span className={styles.probability}>
									<span className={styles.probabilityIcons}>
										<i className={styles.icon}></i>
										<i className={styles.icon}></i>
										<i className={styles.icon}></i>
										<i className={styles.iconInactive}></i>
									</span>
									<span>Muito Boa</span>
								</span>
							</div>
							<div className={styles.gameStatus}>
								<span className={styles.statusBadge}>Premiada!</span>
								<span className={styles.details}>Ver detalhes</span>
								<span className={styles.remaining}>
									restam <span className={styles.remainingCount}>2/25</span>
								</span>
							</div>
						</div>
						<div className={styles.gamePurchase}>
							<div className={styles.price}>R$10</div>
							<div className={styles.cartButton}>
								<i className={styles.cartIcon}></i>
							</div>
						</div>
					</div>

					<Divider dashed />

					<div className={styles.gameContainer}>
						<div className={styles.gameInfo}>
							<div className={styles.gameTitle}>Dia de Sorte</div>
							<div className={styles.gameDetails}>
								<span className={styles.group}>Grupo: ST-HAA-006</span>
								<span className={styles.probability}>
									<span className={styles.probabilityIcons}>
										<i className={styles.icon}></i>
										<i className={styles.icon}></i>
										<i className={styles.icon}></i>
										<i className={styles.iconInactive}></i>
									</span>
									<span>Muito Boa</span>
								</span>
							</div>
							<div className={styles.gameStatus}>
								<span className={styles.statusBadge}>Premiada!</span>
								<span className={styles.details}>Ver detalhes</span>
								<span className={styles.remaining}>
									restam <span className={styles.remainingCount}>2/25</span>
								</span>
							</div>
						</div>
						<div className={styles.gamePurchase}>
							<div className={styles.price}>R$10</div>
							<div className={styles.cartButton}>
								<i className={styles.cartIcon}></i>
							</div>
						</div>
					</div>

					<Divider dashed />

					<div className={styles.gameContainer}>
						<div className={styles.gameInfo}>
							<div className={styles.gameTitle}>Dia de Sorte</div>
							<div className={styles.gameDetails}>
								<span className={styles.group}>Grupo: ST-HAA-006</span>
								<span className={styles.probability}>
									<span className={styles.probabilityIcons}>
										<i className={styles.icon}></i>
										<i className={styles.icon}></i>
										<i className={styles.icon}></i>
										<i className={styles.iconInactive}></i>
									</span>
									<span>Muito Boa</span>
								</span>
							</div>
							<div className={styles.gameStatus}>
								<span className={styles.statusBadge}>Premiada!</span>
								<span className={styles.details}>Ver detalhes</span>
								<span className={styles.remaining}>
									restam <span className={styles.remainingCount}>2/25</span>
								</span>
							</div>
						</div>
						<div className={styles.gamePurchase}>
							<div className={styles.price}>R$10</div>
							<div className={styles.cartButton}>
								<i className={styles.cartIcon}></i>
							</div>
						</div>
					</div>

				</Col>
			</Row>
		</Card >
	);
};

BettingPool.propTypes = propTypes;

BettingPool.defaultProps = defaultProps;

export default withRouter(BettingPool);
