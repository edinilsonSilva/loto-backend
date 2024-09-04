/* eslint-disable no-console */
import React from 'react';
import PropTypes from 'prop-types';

import { Menu, Dropdown, Modal, Row, Col } from 'antd';
import { useSelector, useDispatch } from 'react-redux';

import Router from 'next/router';
import Link from 'next/link';

import { BiKey } from 'react-icons/bi';
import { AiOutlineLogout } from 'react-icons/ai';

import {
	ExclamationCircleOutlined,
	UserOutlined,
} from '@ant-design/icons';

import Avatar from 'src/components/Avatar';

import classes from './style.module.less';
import { LoginService } from 'src/service/LoginService';

const propTypes = {
	style: PropTypes.object,
};

const defaultProps = {
	style: {},
};

const AvatarDropDown = (props) => {

	const loginService = new LoginService();

	const { style } = props;

	const auth = {}

	const paid = {}

	const handleLogout = React.useCallback(async () => {
		Modal.confirm({
			title: 'Are you sure?',
			icon: <ExclamationCircleOutlined />,
			// content: 'Are you sure?',
			onOk: async () => { loginService.doLogout() },
			onCancel() {
				console.log('Cancel');
			},
		});
	}, []);

	const menu = (
		<Menu className={classes.menuDropdown}>
			<Menu.Item key="change">
				<Link href="/minha-conta" className={classes.item}>
					<BiKey />
					<span>Minha conta</span>
				</Link>
			</Menu.Item>

			<Menu.Item key="logout">
				<a className={classes.item} onClick={handleLogout}>
					<AiOutlineLogout />
					<span>Sair</span>
				</a>
			</Menu.Item>
		</Menu>
	);

	return (
		<div className={classes.divInfoAccount}>
			<Row gutter={[8, 8]} justify="center" align="middle">
				<Col>
					<UserOutlined style={{ fontSize: 22 }} />
				</Col>
				<Col>
					<div>
						<p>Ola!</p>
						<p>Edinilson Silva</p>
					</div>
				</Col>
				<Col>
					<Dropdown style={style} overlay={menu} trigger={['click']} placement="bottomRight">
						<div style={{ lineHeight: '50px' }}>
							<Avatar
								size={30}
								src={auth.avatar}
								fullName={auth.fullName}
								className={classes.avatar}
								vip={paid}
							/>
						</div>
					</Dropdown>
				</Col>
			</Row>
		</div>
	);
};

AvatarDropDown.propTypes = propTypes;
AvatarDropDown.defaultProps = defaultProps;

export default AvatarDropDown;
