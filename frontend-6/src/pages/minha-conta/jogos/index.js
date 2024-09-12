// import PropTypes from 'prop-types';

import Head from "src/components/Head";
import GameDashboard from "src/containers/Game/Dashboard";

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
			<Head title="Jogos | Dashboard" />
			<GameDashboard />
		</>
	);
};

GameDashboardPage.propTypes = propTypes;

GameDashboardPage.defaultProps = defaultProps;

export default GameDashboardPage;
