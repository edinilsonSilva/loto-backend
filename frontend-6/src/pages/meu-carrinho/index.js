// import PropTypes from 'prop-types';

import Head from 'src/components/Head';

import MyShoppingCart from 'src/containers/MyShoppingCart';

const propTypes = {
	// classes: PropTypes.object.isRequired,
};

const defaultProps = {
	// classes: {},
};

const MyShoppingCartPage = (props) => {
	// const { } = props;

	return (
		<>
			<Head title="Meu carrinho" />
			<MyShoppingCart />
		</>
	);
};

MyShoppingCartPage.propTypes = propTypes;

MyShoppingCartPage.defaultProps = defaultProps;

export default MyShoppingCartPage;
