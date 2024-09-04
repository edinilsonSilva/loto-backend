import PropTypes from 'prop-types';

import { withRouter } from 'next/router';
import { Card } from 'antd';

const propTypes = {
	router: PropTypes.object.isRequired,
};

const defaultProps = {
	router: {},
};

const MyAccount = (props) => {

	const { router, userList = [] } = props;

	return (
		<Card className="row">
			<span>Minha Conta</span>
		</Card>
	);
};

MyAccount.propTypes = propTypes;

MyAccount.defaultProps = defaultProps;

export default withRouter(MyAccount);
