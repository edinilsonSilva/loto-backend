import PropTypes from 'prop-types';

import { withRouter } from 'next/router';
import { Card } from 'antd';

const propTypes = {
	router: PropTypes.object.isRequired,
};

const defaultProps = {
	router: {},
};

const Contas = (props) => {

	const { router, userList = [] } = props;

	return (
		<Card className="row">
			<span>contas</span>
		</Card>
	);
};

Contas.propTypes = propTypes;

Contas.defaultProps = defaultProps;

export default withRouter(Contas);
