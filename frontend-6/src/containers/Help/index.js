/* --------------------------------------------------------
* Author Trần Đức Tiến
* Email tientran0019@gmail.com
* Phone 0972970075
*
* Created: 2021-06-19 19:14:01
*------------------------------------------------------- */

import React from 'react';
// import PropTypes from 'prop-types';

// import classes from './style.less';

import { html } from '../../../README.md';
import { Card } from 'antd';

const propTypes = {
	// classes: PropTypes.object.isRequired,
};

const defaultProps = {
	// classes: {},
};

const Index = (props) => {
	// const { } = props;

	return (
		<div className="">
			<Card >
				<div dangerouslySetInnerHTML={{ __html: html }} />
			</Card>
		</div>
	);
};

Index.propTypes = propTypes;

Index.defaultProps = defaultProps;

export default Index;
