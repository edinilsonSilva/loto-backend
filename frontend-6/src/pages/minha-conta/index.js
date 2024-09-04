// import PropTypes from 'prop-types';

import Head from 'src/components/Head';

import MyAccount from 'src/containers/MyAccount';

const propTypes = {
	// classes: PropTypes.object.isRequired,
};

const defaultProps = {
	// classes: {},
};

const MyAccountPage = (props) => {
	// const { } = props;

	return (
		<>
			<Head title="Minha Conta" />
			<MyAccount />
		</>
	);
};

MyAccountPage.propTypes = propTypes;

MyAccountPage.defaultProps = defaultProps;

export default MyAccountPage;
