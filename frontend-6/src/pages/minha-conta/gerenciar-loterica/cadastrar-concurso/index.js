// import PropTypes from 'prop-types';

import Head from "src/components/Head";
import CreateContest from "src/containers/ManageLottery/Create/Contest";

const propTypes = {
	// classes: PropTypes.object.isRequired,
};

const defaultProps = {
	// classes: {},
};

const CreateContestPage = (props) => {
	// const { } = props;

	return (
		<>
			<Head title="Novo Concurso" />
			<CreateContest />
		</>
	);
};

CreateContestPage.propTypes = propTypes;

CreateContestPage.defaultProps = defaultProps;

export default CreateContestPage;
