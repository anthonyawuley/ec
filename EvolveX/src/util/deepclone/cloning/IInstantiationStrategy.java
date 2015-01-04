package util.deepclone.cloning;

/**
 * @author kostantinos.kougios
 *
 * 17 Jul 2012
 */
public interface IInstantiationStrategy
{
	<T> T newInstance(final Class<T> c);
}
