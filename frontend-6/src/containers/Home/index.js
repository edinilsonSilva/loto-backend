/* --------------------------------------------------------
* Author Trần Đức Tiến
* Email tientran0019@gmail.com
* Phone 0972970075
*
* Created: 2021-06-19 19:14:01
*------------------------------------------------------- */

import { Card, Carousel, Col, Row } from "antd";

// import PropTypes from 'prop-types';

// import classes from './style.less';


const propTypes = {
	// classes: PropTypes.object.isRequired,
};

const defaultProps = {
	// classes: {},
};

const contentStyle = {
	margin: 0,
	height: '160px',
	color: '#fff',
	lineHeight: '160px',
	textAlign: 'center',
	background: '#364d79',
};

const onChange = (currentSlide) => {
	console.log(currentSlide);
};

const Index = (props) => {
	// const { } = props;

	return (
		<Card style={{ minHeight: '100vh' }}>
			<Carousel arrows infinite={false}>
				<div>
					<h3 style={contentStyle}>1</h3>
				</div>
				<div>
					<h3 style={contentStyle}>2</h3>
				</div>
				<div>
					<h3 style={contentStyle}>3</h3>
				</div>
				<div>
					<h3 style={contentStyle}>4</h3>
				</div>
			</Carousel>

			<Row gutter={[16, 16]}>
				<Col span={8}>
					<Card>Card 1</Card>
				</Col>
				<Col span={8}>
					<Card>Card 2</Card>
				</Col>
				<Col span={8}>
					<Card>Card 3</Card>
				</Col>

				<Col span={8}>
					<Card>Card 4</Card>
				</Col>
				<Col span={8}>
					<Card>Card 5</Card>
				</Col>
				<Col span={8}>
					<Card>Card 6</Card>
				</Col>
			</Row>
		</Card>
	);
};

Index.propTypes = propTypes;

Index.defaultProps = defaultProps;

export default Index;
