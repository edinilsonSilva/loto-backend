// import PropTypes from 'prop-types';

import Head from 'src/components/Head';

import Help from 'src/containers/Help';

const propTypes = {
	// classes: PropTypes.object.isRequired,
};

const defaultProps = {
	// classes: {},
};

const HelpPage = (props) => {
	// const { } = props;

	return (
		<>
			<Head title="Ajuda" />
			<Help />
		</>
	);
};

HelpPage.propTypes = propTypes;

HelpPage.defaultProps = defaultProps;

export default HelpPage;
