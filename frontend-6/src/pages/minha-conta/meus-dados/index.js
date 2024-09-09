// import PropTypes from 'prop-types';

import Head from 'src/components/Head';

import MyData from 'src/containers/MyData';

const propTypes = {
	// classes: PropTypes.object.isRequired,
};

const defaultProps = {
	// classes: {},
};

const MyDataPage = (props) => {
	// const { } = props;

	return (
		<>
			<Head title="Meus dados" />
			<MyData />
		</>
	);
};

MyDataPage.propTypes = propTypes;

MyDataPage.defaultProps = defaultProps;

export default MyDataPage;
