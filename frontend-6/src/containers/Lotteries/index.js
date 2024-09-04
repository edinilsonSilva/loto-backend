import PropTypes from 'prop-types';

import { withRouter } from 'next/router';
import { Card } from 'antd';

const propTypes = {
	router: PropTypes.object.isRequired,
};

const defaultProps = {
	router: {},
};

const Lotteries = (props) => {

	const { router, userList = [] } = props;

	return (
		<Card className="row">
			<span>Loterias</span>
		</Card>
	);
};

Lotteries.propTypes = propTypes;

Lotteries.defaultProps = defaultProps;

export default withRouter(Lotteries);
