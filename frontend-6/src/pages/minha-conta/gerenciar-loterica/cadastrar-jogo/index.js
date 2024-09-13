// import PropTypes from 'prop-types';

import Head from "src/components/Head";
import CreateGame from "src/containers/ManageLottery/Create/Game";

const propTypes = {
	// classes: PropTypes.object.isRequired,
};

const defaultProps = {
	// classes: {},
};

const CreateGamePage = (props) => {
	// const { } = props;

	return (
		<>
			<Head title="Novo Jogo" />
			<CreateGame />
		</>
	);
};

CreateGamePage.propTypes = propTypes;

CreateGamePage.defaultProps = defaultProps;

export default CreateGamePage;
