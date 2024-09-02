import React from 'react';
// import PropTypes from 'prop-types';

import Head from 'src/components/Head';

import Contas from 'src/containers/Contas';

const propTypes = {
	// classes: PropTypes.object.isRequired,
};

const defaultProps = {
	// classes: {},
};

const AntDemoPage = (props) => {
	// const { } = props;

	return (
		<>
			<Head title="Antd Demo" />
			<Contas />
		</>
	);
};

AntDemoPage.propTypes = propTypes;

AntDemoPage.defaultProps = defaultProps;

export default AntDemoPage;
