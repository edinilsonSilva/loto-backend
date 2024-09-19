import PropTypes from "prop-types";

import { withRouter } from "next/router";
import { Card } from "antd";

import React, { useState } from "react";
import {
	Steps,
	Button,
	message,
	Form,
	Input,
	InputNumber,
	Select,
	Radio,
	Result,
} from "antd";
import {
	ShoppingCartOutlined,
	UserOutlined,
	CreditCardOutlined,
	CheckCircleOutlined,
} from "@ant-design/icons";

const { Step } = Steps;
const { Option } = Select;

const propTypes = {
	router: PropTypes.object.isRequired,
};

const defaultProps = {
	router: {},
};

const CartStep = ({ onNext, cart, setCart }) => {
	const handleQuantityChange = (index, value) => {
		const newCart = [...cart];
		newCart[index].quantity = value;
		setCart(newCart);
	};

	const totalAmount = cart.reduce(
		(sum, item) => sum + item.price * item.quantity,
		0
	);

	return (
		<div>
			<h2>Carrinho de Compras</h2>
			{cart.map((item, index) => (
				<div key={index} style={{ marginBottom: "10px" }}>
					<span>
						{item.name} - R$ {item.price.toFixed(2)}{" "}
					</span>
					<InputNumber
						min={1}
						value={item.quantity}
						onChange={(value) => handleQuantityChange(index, value)}
					/>
				</div>
			))}
			<h3>Total: R$ {totalAmount.toFixed(2)}</h3>
			<Button type="primary" onClick={onNext}>
				Próximo
			</Button>
		</div>
	);
};

const IdentificationStep = ({ onNext, onPrev }) => {
	const [form] = Form.useForm();

	const onFinish = (values) => {
		console.log("Identification values:", values);
		onNext();
	};

	return (
		<Form form={form} onFinish={onFinish} layout="vertical">
			<Form.Item name="name" label="Nome" rules={[{ required: true }]}>
				<Input />
			</Form.Item>
			<Form.Item
				name="email"
				label="E-mail"
				rules={[{ required: true, type: "email" }]}
			>
				<Input />
			</Form.Item>
			<Form.Item
				name="phone"
				label="Telefone"
				rules={[{ required: true }]}
			>
				<Input />
			</Form.Item>
			<Form.Item>
				<Button type="primary" htmlType="submit">
					Próximo
				</Button>
				<Button onClick={onPrev} style={{ marginLeft: "10px" }}>
					Voltar
				</Button>
			</Form.Item>
		</Form>
	);
};

const PaymentStep = ({ onNext, onPrev }) => {
	const [form] = Form.useForm();

	const onFinish = (values) => {
		console.log("Payment values:", values);
		onNext();
	};

	return (
		<Form form={form} onFinish={onFinish} layout="vertical">
			<Form.Item
				name="paymentMethod"
				label="Método de Pagamento"
				rules={[{ required: true }]}
			>
				<Radio.Group>
					<Radio value="creditCard">Cartão de Crédito</Radio>
					<Radio value="debitCard">Cartão de Débito</Radio>
					<Radio value="bankTransfer">Transferência Bancária</Radio>
				</Radio.Group>
			</Form.Item>
			<Form.Item
				name="cardNumber"
				label="Número do Cartão"
				rules={[{ required: true }]}
			>
				<Input />
			</Form.Item>
			<Form.Item
				name="expirationDate"
				label="Data de Expiração"
				rules={[{ required: true }]}
			>
				<Input />
			</Form.Item>
			<Form.Item name="cvv" label="CVV" rules={[{ required: true }]}>
				<Input />
			</Form.Item>
			<Form.Item>
				<Button type="primary" htmlType="submit">
					Finalizar Compra
				</Button>
				<Button onClick={onPrev} style={{ marginLeft: "10px" }}>
					Voltar
				</Button>
			</Form.Item>
		</Form>
	);
};

const ConclusionStep = () => (
	<Result
		status="success"
		title="Compra Realizada com Sucesso!"
		subTitle="Obrigado por jogar. Boa sorte!"
		extra={[
			<Button type="primary" key="console">
				Ver Meus Jogos
			</Button>,
			<Button key="buy">Comprar Mais</Button>,
		]}
	/>
);

const MyShoppingCart = (props) => {
	const { router, userList = [] } = props;
	const [current, setCurrent] = useState(0);
	const [cart, setCart] = useState([
		{ name: "Mega-Sena", price: 4.5, quantity: 1 },
		{ name: "Lotofácil", price: 2.5, quantity: 1 },
	]);

	const next = () => {
		setCurrent(current + 1);
	};

	const prev = () => {
		setCurrent(current - 1);
	};

	const steps = [
		{
			title: "Carrinho",
			content: <CartStep onNext={next} cart={cart} setCart={setCart} />,
			icon: <ShoppingCartOutlined />,
		},
		{
			title: "Identificação",
			content: <IdentificationStep onNext={next} onPrev={prev} />,
			icon: <UserOutlined />,
		},
		{
			title: "Pagamento",
			content: <PaymentStep onNext={next} onPrev={prev} />,
			icon: <CreditCardOutlined />,
		},
		{
			title: "Conclusão",
			content: <ConclusionStep />,
			icon: <CheckCircleOutlined />,
		},
	];

	return (
		<Card className="row">
			<div
				style={{ maxWidth: "600px", margin: "0 auto", padding: "20px" }}
			>
				<Steps current={current}>
					{steps.map((item) => (
						<Step
							key={item.title}
							title={item.title}
							icon={item.icon}
						/>
					))}
				</Steps>
				<div style={{ marginTop: "20px" }}>
					{steps[current].content}
				</div>
			</div>
		</Card>
	);
};

MyShoppingCart.propTypes = propTypes;

MyShoppingCart.defaultProps = defaultProps;

export default withRouter(MyShoppingCart);
