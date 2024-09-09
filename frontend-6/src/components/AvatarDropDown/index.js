/* eslint-disable no-console */
import PropTypes from "prop-types";
import React from "react";

import { Col, Dropdown, Menu, Modal, Row } from "antd";
import { useSelector } from "react-redux";

import Link from "next/link";

import { AiOutlineLogout } from "react-icons/ai";
import { BiKey } from "react-icons/bi";

import { DownOutlined, ExclamationCircleOutlined, UserOutlined } from "@ant-design/icons";

import { LoginService } from "src/service/LoginService";
import classes from "./style.module.less";
import { useRouter } from 'next/router'; 

const propTypes = {
	style: PropTypes.object,
};

const defaultProps = {
	style: {},
};

const AvatarDropDown = (props) => {

	const loginService = new LoginService();

	const account = loginService.getAccount();

	const router = useRouter();

	const { style } = props;

	const auth = {};

	const paid = {};

	const handleLogout = React.useCallback(async () => {
		Modal.confirm({
			title: "Are you sure?",
			icon: <ExclamationCircleOutlined />,
			// content: 'Are you sure?',
			onOk: async () => {
				loginService.doLogout(router);
			},
			onCancel() {
				console.log("Cancel");
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
						<p>{account?.name}</p>
					</div>
				</Col>
				<Col>
					<Dropdown
						style={style}
						overlay={menu}
						trigger={["click"]}
						placement="bottomRight"
					>
						<div style={{  justifyContent: 'center', marginTop: '10px'  }}>
							{/* <Avatar
								size={30}
								src={auth.avatar}
								fullName={auth.fullName}
								className={classes.avatar}
								vip={paid}
							/> */}

							<DownOutlined style={{ fontSize: 22, fontWeight: 'bold'}}/>
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
