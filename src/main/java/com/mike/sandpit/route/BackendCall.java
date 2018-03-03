package com.mike.sandpit.route;

/**
 * properties defining the back-end api call.
 * @author mike
 */
public class BackendCall {
	public String httpPath;
	public String paramString;
	public boolean useCache;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((httpPath == null) ? 0 : httpPath.hashCode());
		result = prime * result + ((paramString == null) ? 0 : paramString.hashCode());
		result = prime * result + (useCache ? 1231 : 1237);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BackendCall other = (BackendCall) obj;
		if (httpPath == null) {
			if (other.httpPath != null)
				return false;
		} else if (!httpPath.equals(other.httpPath))
			return false;
		if (paramString == null) {
			if (other.paramString != null)
				return false;
		} else if (!paramString.equals(other.paramString))
			return false;
		if (useCache != other.useCache)
			return false;
		return true;
	}
}
