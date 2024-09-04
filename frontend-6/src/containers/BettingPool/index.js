import PropTypes from 'prop-types';

import { withRouter } from 'next/router';
import { Card } from 'antd';

const propTypes = {
	router: PropTypes.object.isRequired,
};

const defaultProps = {
	router: {},
};

const BettingPool = (props) => {

	const { router, userList = [] } = props;

	return (
		<Card className="row">
			<span>Bolões</span>
		</Card>
	);
};

BettingPool.propTypes = propTypes;

BettingPool.defaultProps = defaultProps;

export default withRouter(BettingPool);
