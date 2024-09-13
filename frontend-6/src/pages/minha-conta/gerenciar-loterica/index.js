// import PropTypes from 'prop-types';

import Head from "src/components/Head";
import GameDashboard from "src/containers/ManageLottery/Dashboard";

const propTypes = {
	// classes: PropTypes.object.isRequired,
};

const defaultProps = {
	// classes: {},
};

const GameDashboardPage = (props) => {
	// const { } = props;

	return (
		<>
			<Head title="Gerenciar Lotérica" />
			<GameDashboard />
		</>
	);
};

GameDashboardPage.propTypes = propTypes;

GameDashboardPage.defaultProps = defaultProps;

export default GameDashboardPage;
