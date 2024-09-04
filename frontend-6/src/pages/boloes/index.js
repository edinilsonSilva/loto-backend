// import PropTypes from 'prop-types';

import Head from 'src/components/Head';
import BettingPool from 'src/containers/BettingPool';


const propTypes = {
	// classes: PropTypes.object.isRequired,
};

const defaultProps = {
	// classes: {},
};

const BettingPoolPage = (props) => {
	// const { } = props;

	return (
		<>
			<Head title="Bolões" />
			<BettingPool />
		</>
	);
};

BettingPoolPage.propTypes = propTypes;

BettingPoolPage.defaultProps = defaultProps;

export default BettingPoolPage;
