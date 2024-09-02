import { Breadcrumb } from 'antd';
import Link from 'next/link';
import { useRouter } from 'next/router';
import { capitalize } from 'lodash';

const BreadcrumbDn = () => {

	const router = useRouter();
	const { asPath } = router;

	// Quebra a rota atual em partes para criar o breadcrumb
	const pathArray = asPath.split('/').filter(path => path);

	return (
		<Breadcrumb style={{
			marginTop: 16,
			paddingLeft: 20,
		}}>
			<Breadcrumb.Item>
				<Link href="/">Home</Link>
			</Breadcrumb.Item>
			{pathArray.map((path, index) => {
				// Constrói a URL para cada breadcrumb
				const url = '/' + pathArray.slice(0, index + 1).join('/');

				return (
					<Breadcrumb.Item key={url}>
						<Link href={url}>{capitalize(path)}</Link>
					</Breadcrumb.Item>
				);
			})}
		</Breadcrumb>
	);
};

export default BreadcrumbDn;