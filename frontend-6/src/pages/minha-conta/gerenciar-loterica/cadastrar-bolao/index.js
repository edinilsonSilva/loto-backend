// import PropTypes from 'prop-types';

import Head from 'src/components/Head';
import CreatePool from 'src/containers/ManageLottery/Create/Pool';


const propTypes = {
	// classes: PropTypes.object.isRequired,
};

const defaultProps = {
	// classes: {},
};

const CreatePoolPage = (props) => {
	// const { } = props;

	return (
		<>
			<Head title="Novo Bolão" />
			<CreatePool />
		</>
	);
};

CreatePoolPage.propTypes = propTypes;

CreatePoolPage.defaultProps = defaultProps;

export default CreatePoolPage;
