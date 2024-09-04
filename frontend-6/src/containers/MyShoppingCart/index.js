import PropTypes from 'prop-types';

import { withRouter } from 'next/router';
import { Card } from 'antd';

const propTypes = {
	router: PropTypes.object.isRequired,
};

const defaultProps = {
	router: {},
};

const MyShoppingCart = (props) => {

	const { router, userList = [] } = props;

	return (
		<Card className="row">
			<span>Meu carrinho</span>
		</Card>
	);
};

MyShoppingCart.propTypes = propTypes;

MyShoppingCart.defaultProps = defaultProps;

export default withRouter(MyShoppingCart);
