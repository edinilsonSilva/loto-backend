// import PropTypes from 'prop-types';

import Head from 'src/components/Head';
import Lotteries from 'src/containers/Lotteries';

const propTypes = {
	// classes: PropTypes.object.isRequired,
};

const defaultProps = {
	// classes: {},
};

const LotteriesPage = (props) => {
	// const { } = props;

	return (
		<>
			<Head title="Loterias" />
			<Lotteries />
		</>
	);
};

LotteriesPage.propTypes = propTypes;

LotteriesPage.defaultProps = defaultProps;

export default LotteriesPage;
