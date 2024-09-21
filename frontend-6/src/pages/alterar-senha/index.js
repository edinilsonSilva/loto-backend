// import PropTypes from 'prop-types';

import { useRouter } from 'next/router';
import Head from 'src/components/Head';
import ResetPassword from 'src/containers/Auth/ResetPassword';

const propTypes = {
	// classes: PropTypes.object.isRequired,
};

const defaultProps = {
	// classes: {},
};

const ResetPasswordPage = (props) => {
	// const { } = props;

	const router = useRouter();
	const { token } = router.query; // Captura o valor da query string

	console.log(token);

	return (
		<>
			<Head title="Loterias" />
			<ResetPassword token={token}/>
		</>
	);
};

ResetPasswordPage.propTypes = propTypes;

ResetPasswordPage.defaultProps = defaultProps;

export default ResetPasswordPage;
