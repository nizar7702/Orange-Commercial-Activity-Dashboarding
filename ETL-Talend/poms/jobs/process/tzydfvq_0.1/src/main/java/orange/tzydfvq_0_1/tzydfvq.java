// ============================================================================
//
// Copyright (c) 2006-2015, Talend SA
//
// Ce code source a été automatiquement généré par_Talend Open Studio for Data Integration
// / Soumis à la Licence Apache, Version 2.0 (la "Licence") ;
// votre utilisation de ce fichier doit respecter les termes de la Licence.
// Vous pouvez obtenir une copie de la Licence sur
// http://www.apache.org/licenses/LICENSE-2.0
// 
// Sauf lorsqu'explicitement prévu par la loi en vigueur ou accepté par écrit, le logiciel
// distribué sous la Licence est distribué "TEL QUEL",
// SANS GARANTIE OU CONDITION D'AUCUNE SORTE, expresse ou implicite.
// Consultez la Licence pour connaître la terminologie spécifique régissant les autorisations et
// les limites prévues par la Licence.

package orange.tzydfvq_0_1;

import routines.Numeric;
import routines.DataOperation;
import routines.TalendDataGenerator;
import routines.TalendStringUtil;
import routines.TalendString;
import routines.StringHandling;
import routines.Relational;
import routines.TalendDate;
import routines.Mathematical;
import routines.system.*;
import routines.system.api.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.Comparator;

@SuppressWarnings("unused")

/**
 * Job: tzydfvq Purpose: <br>
 * Description: <br>
 * 
 * @author user@talend.com
 * @version 8.0.1.20211109_1610
 * @status
 */
public class tzydfvq implements TalendJob {

	protected static void logIgnoredError(String message, Throwable cause) {
		System.err.println(message);
		if (cause != null) {
			cause.printStackTrace();
		}

	}

	public final Object obj = new Object();

	// for transmiting parameters purpose
	private Object valueObject = null;

	public Object getValueObject() {
		return this.valueObject;
	}

	public void setValueObject(Object valueObject) {
		this.valueObject = valueObject;
	}

	private final static String defaultCharset = java.nio.charset.Charset.defaultCharset().name();

	private final static String utf8Charset = "UTF-8";

	// contains type for every context property
	public class PropertiesWithType extends java.util.Properties {
		private static final long serialVersionUID = 1L;
		private java.util.Map<String, String> propertyTypes = new java.util.HashMap<>();

		public PropertiesWithType(java.util.Properties properties) {
			super(properties);
		}

		public PropertiesWithType() {
			super();
		}

		public void setContextType(String key, String type) {
			propertyTypes.put(key, type);
		}

		public String getContextType(String key) {
			return propertyTypes.get(key);
		}
	}

	// create and load default properties
	private java.util.Properties defaultProps = new java.util.Properties();

	// create application properties with default
	public class ContextProperties extends PropertiesWithType {

		private static final long serialVersionUID = 1L;

		public ContextProperties(java.util.Properties properties) {
			super(properties);
		}

		public ContextProperties() {
			super();
		}

		public void synchronizeContext() {

		}

		// if the stored or passed value is "<TALEND_NULL>" string, it mean null
		public String getStringValue(String key) {
			String origin_value = this.getProperty(key);
			if (NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY.equals(origin_value)) {
				return null;
			}
			return origin_value;
		}

	}

	protected ContextProperties context = new ContextProperties(); // will be instanciated by MS.

	public ContextProperties getContext() {
		return this.context;
	}

	private final String jobVersion = "0.1";
	private final String jobName = "tzydfvq";
	private final String projectName = "ORANGE";
	public Integer errorCode = null;
	private String currentComponent = "";

	private final java.util.Map<String, Object> globalMap = new java.util.HashMap<String, Object>();
	private final static java.util.Map<String, Object> junitGlobalMap = new java.util.HashMap<String, Object>();

	private final java.util.Map<String, Long> start_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Long> end_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Boolean> ok_Hash = new java.util.HashMap<String, Boolean>();
	public final java.util.List<String[]> globalBuffer = new java.util.ArrayList<String[]>();

	private RunStat runStat = new RunStat();

	// OSGi DataSource
	private final static String KEY_DB_DATASOURCES = "KEY_DB_DATASOURCES";

	private final static String KEY_DB_DATASOURCES_RAW = "KEY_DB_DATASOURCES_RAW";

	public void setDataSources(java.util.Map<String, javax.sql.DataSource> dataSources) {
		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		for (java.util.Map.Entry<String, javax.sql.DataSource> dataSourceEntry : dataSources.entrySet()) {
			talendDataSources.put(dataSourceEntry.getKey(),
					new routines.system.TalendDataSource(dataSourceEntry.getValue()));
		}
		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	public void setDataSourceReferences(List serviceReferences) throws Exception {

		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		java.util.Map<String, javax.sql.DataSource> dataSources = new java.util.HashMap<String, javax.sql.DataSource>();

		for (java.util.Map.Entry<String, javax.sql.DataSource> entry : BundleUtils
				.getServices(serviceReferences, javax.sql.DataSource.class).entrySet()) {
			dataSources.put(entry.getKey(), entry.getValue());
			talendDataSources.put(entry.getKey(), new routines.system.TalendDataSource(entry.getValue()));
		}

		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	private final java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
	private final java.io.PrintStream errorMessagePS = new java.io.PrintStream(new java.io.BufferedOutputStream(baos));

	public String getExceptionStackTrace() {
		if ("failure".equals(this.getStatus())) {
			errorMessagePS.flush();
			return baos.toString();
		}
		return null;
	}

	private Exception exception;

	public Exception getException() {
		if ("failure".equals(this.getStatus())) {
			return this.exception;
		}
		return null;
	}

	private class TalendException extends Exception {

		private static final long serialVersionUID = 1L;

		private java.util.Map<String, Object> globalMap = null;
		private Exception e = null;
		private String currentComponent = null;
		private String virtualComponentName = null;

		public void setVirtualComponentName(String virtualComponentName) {
			this.virtualComponentName = virtualComponentName;
		}

		private TalendException(Exception e, String errorComponent, final java.util.Map<String, Object> globalMap) {
			this.currentComponent = errorComponent;
			this.globalMap = globalMap;
			this.e = e;
		}

		public Exception getException() {
			return this.e;
		}

		public String getCurrentComponent() {
			return this.currentComponent;
		}

		public String getExceptionCauseMessage(Exception e) {
			Throwable cause = e;
			String message = null;
			int i = 10;
			while (null != cause && 0 < i--) {
				message = cause.getMessage();
				if (null == message) {
					cause = cause.getCause();
				} else {
					break;
				}
			}
			if (null == message) {
				message = e.getClass().getName();
			}
			return message;
		}

		@Override
		public void printStackTrace() {
			if (!(e instanceof TalendException || e instanceof TDieException)) {
				if (virtualComponentName != null && currentComponent.indexOf(virtualComponentName + "_") == 0) {
					globalMap.put(virtualComponentName + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				}
				globalMap.put(currentComponent + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				System.err.println("Exception in component " + currentComponent + " (" + jobName + ")");
			}
			if (!(e instanceof TDieException)) {
				if (e instanceof TalendException) {
					e.printStackTrace();
				} else {
					e.printStackTrace();
					e.printStackTrace(errorMessagePS);
					tzydfvq.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(tzydfvq.this, new Object[] { e, currentComponent, globalMap });
							break;
						}
					}

					if (!(e instanceof TDieException)) {
					}
				} catch (Exception e) {
					this.e.printStackTrace();
				}
			}
		}
	}

	public void tDBInput_14_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_35_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_49_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_35_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tUniqRow_13_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_35_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBOutput_14_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_35_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_15_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_35_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_50_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_35_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tUniqRow_14_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_35_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBOutput_15_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_35_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_16_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_35_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_51_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_35_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tUniqRow_15_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_35_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBOutput_16_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_35_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_17_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_35_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_52_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_35_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tUniqRow_16_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_35_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBOutput_17_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_35_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_18_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_35_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_53_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_35_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tUniqRow_17_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_35_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBOutput_18_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_35_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputExcel_35_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_35_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_48_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_35_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row62_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_35_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row65_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_35_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row67_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_35_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row69_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_35_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row72_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_35_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputExcel_35_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public static class row62Struct implements routines.system.IPersistableComparableLookupRow<row62Struct> {
		final static byte[] commonByteArrayLock_ORANGE_tzydfvq = new byte[0];
		static byte[] commonByteArray_ORANGE_tzydfvq = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int offre_id;

		public int getOffre_id() {
			return this.offre_id;
		}

		public String Offre;

		public String getOffre() {
			return this.Offre;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.Offre == null) ? 0 : this.Offre.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row62Struct other = (row62Struct) obj;

			if (this.Offre == null) {
				if (other.Offre != null)
					return false;

			} else if (!this.Offre.equals(other.Offre))

				return false;

			return true;
		}

		public void copyDataTo(row62Struct other) {

			other.offre_id = this.offre_id;
			other.Offre = this.Offre;

		}

		public void copyKeysDataTo(row62Struct other) {

			other.Offre = this.Offre;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_tzydfvq.length) {
					if (length < 1024 && commonByteArray_ORANGE_tzydfvq.length == 0) {
						commonByteArray_ORANGE_tzydfvq = new byte[1024];
					} else {
						commonByteArray_ORANGE_tzydfvq = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_tzydfvq, 0, length);
				strReturn = new String(commonByteArray_ORANGE_tzydfvq, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_tzydfvq.length) {
					if (length < 1024 && commonByteArray_ORANGE_tzydfvq.length == 0) {
						commonByteArray_ORANGE_tzydfvq = new byte[1024];
					} else {
						commonByteArray_ORANGE_tzydfvq = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_tzydfvq, 0, length);
				strReturn = new String(commonByteArray_ORANGE_tzydfvq, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readKeysData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ORANGE_tzydfvq) {

				try {

					int length = 0;

					this.Offre = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_tzydfvq) {

				try {

					int length = 0;

					this.Offre = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Offre, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Offre, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		/**
		 * Fill Values data by reading ObjectInputStream.
		 */
		public void readValuesData(DataInputStream dis, ObjectInputStream ois) {
			try {

				int length = 0;

				this.offre_id = dis.readInt();

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

				this.offre_id = objectIn.readInt();

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				dos.writeInt(this.offre_id);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				objectOut.writeInt(this.offre_id);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		public boolean supportMarshaller() {
			return true;
		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("offre_id=" + String.valueOf(offre_id));
			sb.append(",Offre=" + Offre);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row62Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.Offre, other.Offre);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tDBInput_14Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBInput_14_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row62Struct row62 = new row62Struct();

				/**
				 * [tAdvancedHash_row62 begin ] start
				 */

				ok_Hash.put("tAdvancedHash_row62", false);
				start_Hash.put("tAdvancedHash_row62", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_row62";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row62");
				}

				int tos_count_tAdvancedHash_row62 = 0;

				// connection name:row62
				// source node:tDBInput_14 - inputs:(after_tFileInputExcel_35)
				// outputs:(row62,row62) | target node:tAdvancedHash_row62 - inputs:(row62)
				// outputs:()
				// linked node: tMap_49 - inputs:(row62,Dim_offre_mobile) outputs:(c)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row62 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row62Struct> tHash_Lookup_row62 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row62Struct>getLookup(matchingModeEnum_row62);

				globalMap.put("tHash_Lookup_row62", tHash_Lookup_row62);

				/**
				 * [tAdvancedHash_row62 begin ] stop
				 */

				/**
				 * [tDBInput_14 begin ] start
				 */

				ok_Hash.put("tDBInput_14", false);
				start_Hash.put("tDBInput_14", System.currentTimeMillis());

				currentComponent = "tDBInput_14";

				int tos_count_tDBInput_14 = 0;

				org.talend.designer.components.util.mssql.MSSqlGenerateTimestampUtil mssqlGTU_tDBInput_14 = org.talend.designer.components.util.mssql.MSSqlUtilFactory
						.getMSSqlGenerateTimestampUtil();

				java.util.List<String> talendToDBList_tDBInput_14 = new java.util.ArrayList();
				String[] talendToDBArray_tDBInput_14 = new String[] { "FLOAT", "NUMERIC", "NUMERIC IDENTITY", "DECIMAL",
						"DECIMAL IDENTITY", "REAL" };
				java.util.Collections.addAll(talendToDBList_tDBInput_14, talendToDBArray_tDBInput_14);
				int nb_line_tDBInput_14 = 0;
				java.sql.Connection conn_tDBInput_14 = null;
				String driverClass_tDBInput_14 = "net.sourceforge.jtds.jdbc.Driver";
				java.lang.Class jdbcclazz_tDBInput_14 = java.lang.Class.forName(driverClass_tDBInput_14);
				String dbUser_tDBInput_14 = "sa";

				final String decryptedPassword_tDBInput_14 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:1mlvvq4qQCpzDWpog3iSx2hpITNmsHG6prZv5V6wz6j6hwTRb5U=");

				String dbPwd_tDBInput_14 = decryptedPassword_tDBInput_14;

				String port_tDBInput_14 = "1433";
				String dbname_tDBInput_14 = "orange_DW__";
				String url_tDBInput_14 = "jdbc:jtds:sqlserver://" + "DESKTOP-6RL9E91";
				if (!"".equals(port_tDBInput_14)) {
					url_tDBInput_14 += ":" + "1433";
				}
				if (!"".equals(dbname_tDBInput_14)) {
					url_tDBInput_14 += "//" + "orange_DW__";
				}
				url_tDBInput_14 += ";appName=" + projectName + ";" + "";
				String dbschema_tDBInput_14 = "dbo";

				conn_tDBInput_14 = java.sql.DriverManager.getConnection(url_tDBInput_14, dbUser_tDBInput_14,
						dbPwd_tDBInput_14);

				java.sql.Statement stmt_tDBInput_14 = conn_tDBInput_14.createStatement();

				String dbquery_tDBInput_14 = "SELECT dbo.Dim_offre.offre_id,\n		dbo.Dim_offre.Offre\nFROM	dbo.Dim_offre";

				globalMap.put("tDBInput_14_QUERY", dbquery_tDBInput_14);
				java.sql.ResultSet rs_tDBInput_14 = null;

				try {
					rs_tDBInput_14 = stmt_tDBInput_14.executeQuery(dbquery_tDBInput_14);
					java.sql.ResultSetMetaData rsmd_tDBInput_14 = rs_tDBInput_14.getMetaData();
					int colQtyInRs_tDBInput_14 = rsmd_tDBInput_14.getColumnCount();

					String tmpContent_tDBInput_14 = null;

					while (rs_tDBInput_14.next()) {
						nb_line_tDBInput_14++;

						if (colQtyInRs_tDBInput_14 < 1) {
							row62.offre_id = 0;
						} else {

							row62.offre_id = rs_tDBInput_14.getInt(1);
							if (rs_tDBInput_14.wasNull()) {
								throw new RuntimeException("Null value in non-Nullable column");
							}
						}
						if (colQtyInRs_tDBInput_14 < 2) {
							row62.Offre = null;
						} else {

							tmpContent_tDBInput_14 = rs_tDBInput_14.getString(2);
							if (tmpContent_tDBInput_14 != null) {
								if (talendToDBList_tDBInput_14.contains(
										rsmd_tDBInput_14.getColumnTypeName(2).toUpperCase(java.util.Locale.ENGLISH))) {
									row62.Offre = FormatterUtils.formatUnwithE(tmpContent_tDBInput_14);
								} else {
									row62.Offre = tmpContent_tDBInput_14;
								}
							} else {
								row62.Offre = null;
							}
						}

						/**
						 * [tDBInput_14 begin ] stop
						 */

						/**
						 * [tDBInput_14 main ] start
						 */

						currentComponent = "tDBInput_14";

						tos_count_tDBInput_14++;

						/**
						 * [tDBInput_14 main ] stop
						 */

						/**
						 * [tDBInput_14 process_data_begin ] start
						 */

						currentComponent = "tDBInput_14";

						/**
						 * [tDBInput_14 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row62 main ] start
						 */

						currentComponent = "tAdvancedHash_row62";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row62"

							);
						}

						row62Struct row62_HashRow = new row62Struct();

						row62_HashRow.offre_id = row62.offre_id;

						row62_HashRow.Offre = row62.Offre;

						tHash_Lookup_row62.put(row62_HashRow);

						tos_count_tAdvancedHash_row62++;

						/**
						 * [tAdvancedHash_row62 main ] stop
						 */

						/**
						 * [tAdvancedHash_row62 process_data_begin ] start
						 */

						currentComponent = "tAdvancedHash_row62";

						/**
						 * [tAdvancedHash_row62 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row62 process_data_end ] start
						 */

						currentComponent = "tAdvancedHash_row62";

						/**
						 * [tAdvancedHash_row62 process_data_end ] stop
						 */

						/**
						 * [tDBInput_14 process_data_end ] start
						 */

						currentComponent = "tDBInput_14";

						/**
						 * [tDBInput_14 process_data_end ] stop
						 */

						/**
						 * [tDBInput_14 end ] start
						 */

						currentComponent = "tDBInput_14";

					}
				} finally {
					if (rs_tDBInput_14 != null) {
						rs_tDBInput_14.close();
					}
					if (stmt_tDBInput_14 != null) {
						stmt_tDBInput_14.close();
					}
					if (conn_tDBInput_14 != null && !conn_tDBInput_14.isClosed()) {

						conn_tDBInput_14.close();

						if ("com.mysql.cj.jdbc.Driver".equals((String) globalMap.get("driverClass_"))
								&& routines.system.BundleUtils.inOSGi()) {
							Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread")
									.getMethod("checkedShutdown").invoke(null, (Object[]) null);
						}

					}
				}
				globalMap.put("tDBInput_14_NB_LINE", nb_line_tDBInput_14);

				ok_Hash.put("tDBInput_14", true);
				end_Hash.put("tDBInput_14", System.currentTimeMillis());

				/**
				 * [tDBInput_14 end ] stop
				 */

				/**
				 * [tAdvancedHash_row62 end ] start
				 */

				currentComponent = "tAdvancedHash_row62";

				tHash_Lookup_row62.endPut();

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row62");
				}

				ok_Hash.put("tAdvancedHash_row62", true);
				end_Hash.put("tAdvancedHash_row62", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row62 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tDBInput_14 finally ] start
				 */

				currentComponent = "tDBInput_14";

				/**
				 * [tDBInput_14 finally ] stop
				 */

				/**
				 * [tAdvancedHash_row62 finally ] start
				 */

				currentComponent = "tAdvancedHash_row62";

				/**
				 * [tAdvancedHash_row62 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBInput_14_SUBPROCESS_STATE", 1);
	}

	public static class row65Struct implements routines.system.IPersistableComparableLookupRow<row65Struct> {
		final static byte[] commonByteArrayLock_ORANGE_tzydfvq = new byte[0];
		static byte[] commonByteArray_ORANGE_tzydfvq = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int nature_offre_id;

		public int getNature_offre_id() {
			return this.nature_offre_id;
		}

		public String Nature_Offre;

		public String getNature_Offre() {
			return this.Nature_Offre;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.Nature_Offre == null) ? 0 : this.Nature_Offre.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row65Struct other = (row65Struct) obj;

			if (this.Nature_Offre == null) {
				if (other.Nature_Offre != null)
					return false;

			} else if (!this.Nature_Offre.equals(other.Nature_Offre))

				return false;

			return true;
		}

		public void copyDataTo(row65Struct other) {

			other.nature_offre_id = this.nature_offre_id;
			other.Nature_Offre = this.Nature_Offre;

		}

		public void copyKeysDataTo(row65Struct other) {

			other.Nature_Offre = this.Nature_Offre;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_tzydfvq.length) {
					if (length < 1024 && commonByteArray_ORANGE_tzydfvq.length == 0) {
						commonByteArray_ORANGE_tzydfvq = new byte[1024];
					} else {
						commonByteArray_ORANGE_tzydfvq = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_tzydfvq, 0, length);
				strReturn = new String(commonByteArray_ORANGE_tzydfvq, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_tzydfvq.length) {
					if (length < 1024 && commonByteArray_ORANGE_tzydfvq.length == 0) {
						commonByteArray_ORANGE_tzydfvq = new byte[1024];
					} else {
						commonByteArray_ORANGE_tzydfvq = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_tzydfvq, 0, length);
				strReturn = new String(commonByteArray_ORANGE_tzydfvq, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readKeysData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ORANGE_tzydfvq) {

				try {

					int length = 0;

					this.Nature_Offre = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_tzydfvq) {

				try {

					int length = 0;

					this.Nature_Offre = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Nature_Offre, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Nature_Offre, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		/**
		 * Fill Values data by reading ObjectInputStream.
		 */
		public void readValuesData(DataInputStream dis, ObjectInputStream ois) {
			try {

				int length = 0;

				this.nature_offre_id = dis.readInt();

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

				this.nature_offre_id = objectIn.readInt();

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				dos.writeInt(this.nature_offre_id);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				objectOut.writeInt(this.nature_offre_id);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		public boolean supportMarshaller() {
			return true;
		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("nature_offre_id=" + String.valueOf(nature_offre_id));
			sb.append(",Nature_Offre=" + Nature_Offre);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row65Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.Nature_Offre, other.Nature_Offre);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tDBInput_15Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBInput_15_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row65Struct row65 = new row65Struct();

				/**
				 * [tAdvancedHash_row65 begin ] start
				 */

				ok_Hash.put("tAdvancedHash_row65", false);
				start_Hash.put("tAdvancedHash_row65", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_row65";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row65");
				}

				int tos_count_tAdvancedHash_row65 = 0;

				// connection name:row65
				// source node:tDBInput_15 - inputs:(after_tFileInputExcel_35)
				// outputs:(row65,row65) | target node:tAdvancedHash_row65 - inputs:(row65)
				// outputs:()
				// linked node: tMap_50 - inputs:(row65,Dim_nature_offre_mobile) outputs:(v)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row65 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row65Struct> tHash_Lookup_row65 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row65Struct>getLookup(matchingModeEnum_row65);

				globalMap.put("tHash_Lookup_row65", tHash_Lookup_row65);

				/**
				 * [tAdvancedHash_row65 begin ] stop
				 */

				/**
				 * [tDBInput_15 begin ] start
				 */

				ok_Hash.put("tDBInput_15", false);
				start_Hash.put("tDBInput_15", System.currentTimeMillis());

				currentComponent = "tDBInput_15";

				int tos_count_tDBInput_15 = 0;

				org.talend.designer.components.util.mssql.MSSqlGenerateTimestampUtil mssqlGTU_tDBInput_15 = org.talend.designer.components.util.mssql.MSSqlUtilFactory
						.getMSSqlGenerateTimestampUtil();

				java.util.List<String> talendToDBList_tDBInput_15 = new java.util.ArrayList();
				String[] talendToDBArray_tDBInput_15 = new String[] { "FLOAT", "NUMERIC", "NUMERIC IDENTITY", "DECIMAL",
						"DECIMAL IDENTITY", "REAL" };
				java.util.Collections.addAll(talendToDBList_tDBInput_15, talendToDBArray_tDBInput_15);
				int nb_line_tDBInput_15 = 0;
				java.sql.Connection conn_tDBInput_15 = null;
				String driverClass_tDBInput_15 = "net.sourceforge.jtds.jdbc.Driver";
				java.lang.Class jdbcclazz_tDBInput_15 = java.lang.Class.forName(driverClass_tDBInput_15);
				String dbUser_tDBInput_15 = "sa";

				final String decryptedPassword_tDBInput_15 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:UsNXZNJ2LT4iYihtGXiOECzgrdYotbzvwQjU+8XejmMnWk5N67Y=");

				String dbPwd_tDBInput_15 = decryptedPassword_tDBInput_15;

				String port_tDBInput_15 = "1433";
				String dbname_tDBInput_15 = "orange_DW__";
				String url_tDBInput_15 = "jdbc:jtds:sqlserver://" + "DESKTOP-6RL9E91";
				if (!"".equals(port_tDBInput_15)) {
					url_tDBInput_15 += ":" + "1433";
				}
				if (!"".equals(dbname_tDBInput_15)) {
					url_tDBInput_15 += "//" + "orange_DW__";
				}
				url_tDBInput_15 += ";appName=" + projectName + ";" + "";
				String dbschema_tDBInput_15 = "dbo";

				conn_tDBInput_15 = java.sql.DriverManager.getConnection(url_tDBInput_15, dbUser_tDBInput_15,
						dbPwd_tDBInput_15);

				java.sql.Statement stmt_tDBInput_15 = conn_tDBInput_15.createStatement();

				String dbquery_tDBInput_15 = "SELECT dbo.Dim_nature_offre.nature_offre_id,\n		dbo.Dim_nature_offre.Nature_Offre\nFROM	dbo.Dim_nature_offre";

				globalMap.put("tDBInput_15_QUERY", dbquery_tDBInput_15);
				java.sql.ResultSet rs_tDBInput_15 = null;

				try {
					rs_tDBInput_15 = stmt_tDBInput_15.executeQuery(dbquery_tDBInput_15);
					java.sql.ResultSetMetaData rsmd_tDBInput_15 = rs_tDBInput_15.getMetaData();
					int colQtyInRs_tDBInput_15 = rsmd_tDBInput_15.getColumnCount();

					String tmpContent_tDBInput_15 = null;

					while (rs_tDBInput_15.next()) {
						nb_line_tDBInput_15++;

						if (colQtyInRs_tDBInput_15 < 1) {
							row65.nature_offre_id = 0;
						} else {

							row65.nature_offre_id = rs_tDBInput_15.getInt(1);
							if (rs_tDBInput_15.wasNull()) {
								throw new RuntimeException("Null value in non-Nullable column");
							}
						}
						if (colQtyInRs_tDBInput_15 < 2) {
							row65.Nature_Offre = null;
						} else {

							tmpContent_tDBInput_15 = rs_tDBInput_15.getString(2);
							if (tmpContent_tDBInput_15 != null) {
								if (talendToDBList_tDBInput_15.contains(
										rsmd_tDBInput_15.getColumnTypeName(2).toUpperCase(java.util.Locale.ENGLISH))) {
									row65.Nature_Offre = FormatterUtils.formatUnwithE(tmpContent_tDBInput_15);
								} else {
									row65.Nature_Offre = tmpContent_tDBInput_15;
								}
							} else {
								row65.Nature_Offre = null;
							}
						}

						/**
						 * [tDBInput_15 begin ] stop
						 */

						/**
						 * [tDBInput_15 main ] start
						 */

						currentComponent = "tDBInput_15";

						tos_count_tDBInput_15++;

						/**
						 * [tDBInput_15 main ] stop
						 */

						/**
						 * [tDBInput_15 process_data_begin ] start
						 */

						currentComponent = "tDBInput_15";

						/**
						 * [tDBInput_15 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row65 main ] start
						 */

						currentComponent = "tAdvancedHash_row65";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row65"

							);
						}

						row65Struct row65_HashRow = new row65Struct();

						row65_HashRow.nature_offre_id = row65.nature_offre_id;

						row65_HashRow.Nature_Offre = row65.Nature_Offre;

						tHash_Lookup_row65.put(row65_HashRow);

						tos_count_tAdvancedHash_row65++;

						/**
						 * [tAdvancedHash_row65 main ] stop
						 */

						/**
						 * [tAdvancedHash_row65 process_data_begin ] start
						 */

						currentComponent = "tAdvancedHash_row65";

						/**
						 * [tAdvancedHash_row65 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row65 process_data_end ] start
						 */

						currentComponent = "tAdvancedHash_row65";

						/**
						 * [tAdvancedHash_row65 process_data_end ] stop
						 */

						/**
						 * [tDBInput_15 process_data_end ] start
						 */

						currentComponent = "tDBInput_15";

						/**
						 * [tDBInput_15 process_data_end ] stop
						 */

						/**
						 * [tDBInput_15 end ] start
						 */

						currentComponent = "tDBInput_15";

					}
				} finally {
					if (rs_tDBInput_15 != null) {
						rs_tDBInput_15.close();
					}
					if (stmt_tDBInput_15 != null) {
						stmt_tDBInput_15.close();
					}
					if (conn_tDBInput_15 != null && !conn_tDBInput_15.isClosed()) {

						conn_tDBInput_15.close();

						if ("com.mysql.cj.jdbc.Driver".equals((String) globalMap.get("driverClass_"))
								&& routines.system.BundleUtils.inOSGi()) {
							Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread")
									.getMethod("checkedShutdown").invoke(null, (Object[]) null);
						}

					}
				}
				globalMap.put("tDBInput_15_NB_LINE", nb_line_tDBInput_15);

				ok_Hash.put("tDBInput_15", true);
				end_Hash.put("tDBInput_15", System.currentTimeMillis());

				/**
				 * [tDBInput_15 end ] stop
				 */

				/**
				 * [tAdvancedHash_row65 end ] start
				 */

				currentComponent = "tAdvancedHash_row65";

				tHash_Lookup_row65.endPut();

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row65");
				}

				ok_Hash.put("tAdvancedHash_row65", true);
				end_Hash.put("tAdvancedHash_row65", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row65 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tDBInput_15 finally ] start
				 */

				currentComponent = "tDBInput_15";

				/**
				 * [tDBInput_15 finally ] stop
				 */

				/**
				 * [tAdvancedHash_row65 finally ] start
				 */

				currentComponent = "tAdvancedHash_row65";

				/**
				 * [tAdvancedHash_row65 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBInput_15_SUBPROCESS_STATE", 1);
	}

	public static class row67Struct implements routines.system.IPersistableComparableLookupRow<row67Struct> {
		final static byte[] commonByteArrayLock_ORANGE_tzydfvq = new byte[0];
		static byte[] commonByteArray_ORANGE_tzydfvq = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int Dim_Reference_OSM_Opportunite_id;

		public int getDim_Reference_OSM_Opportunite_id() {
			return this.Dim_Reference_OSM_Opportunite_id;
		}

		public String Reference_OSM__Opportunite;

		public String getReference_OSM__Opportunite() {
			return this.Reference_OSM__Opportunite;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result
						+ ((this.Reference_OSM__Opportunite == null) ? 0 : this.Reference_OSM__Opportunite.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row67Struct other = (row67Struct) obj;

			if (this.Reference_OSM__Opportunite == null) {
				if (other.Reference_OSM__Opportunite != null)
					return false;

			} else if (!this.Reference_OSM__Opportunite.equals(other.Reference_OSM__Opportunite))

				return false;

			return true;
		}

		public void copyDataTo(row67Struct other) {

			other.Dim_Reference_OSM_Opportunite_id = this.Dim_Reference_OSM_Opportunite_id;
			other.Reference_OSM__Opportunite = this.Reference_OSM__Opportunite;

		}

		public void copyKeysDataTo(row67Struct other) {

			other.Reference_OSM__Opportunite = this.Reference_OSM__Opportunite;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_tzydfvq.length) {
					if (length < 1024 && commonByteArray_ORANGE_tzydfvq.length == 0) {
						commonByteArray_ORANGE_tzydfvq = new byte[1024];
					} else {
						commonByteArray_ORANGE_tzydfvq = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_tzydfvq, 0, length);
				strReturn = new String(commonByteArray_ORANGE_tzydfvq, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_tzydfvq.length) {
					if (length < 1024 && commonByteArray_ORANGE_tzydfvq.length == 0) {
						commonByteArray_ORANGE_tzydfvq = new byte[1024];
					} else {
						commonByteArray_ORANGE_tzydfvq = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_tzydfvq, 0, length);
				strReturn = new String(commonByteArray_ORANGE_tzydfvq, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readKeysData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ORANGE_tzydfvq) {

				try {

					int length = 0;

					this.Reference_OSM__Opportunite = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_tzydfvq) {

				try {

					int length = 0;

					this.Reference_OSM__Opportunite = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Reference_OSM__Opportunite, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Reference_OSM__Opportunite, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		/**
		 * Fill Values data by reading ObjectInputStream.
		 */
		public void readValuesData(DataInputStream dis, ObjectInputStream ois) {
			try {

				int length = 0;

				this.Dim_Reference_OSM_Opportunite_id = dis.readInt();

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

				this.Dim_Reference_OSM_Opportunite_id = objectIn.readInt();

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				dos.writeInt(this.Dim_Reference_OSM_Opportunite_id);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				objectOut.writeInt(this.Dim_Reference_OSM_Opportunite_id);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		public boolean supportMarshaller() {
			return true;
		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Dim_Reference_OSM_Opportunite_id=" + String.valueOf(Dim_Reference_OSM_Opportunite_id));
			sb.append(",Reference_OSM__Opportunite=" + Reference_OSM__Opportunite);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row67Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.Reference_OSM__Opportunite, other.Reference_OSM__Opportunite);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tDBInput_16Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBInput_16_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row67Struct row67 = new row67Struct();

				/**
				 * [tAdvancedHash_row67 begin ] start
				 */

				ok_Hash.put("tAdvancedHash_row67", false);
				start_Hash.put("tAdvancedHash_row67", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_row67";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row67");
				}

				int tos_count_tAdvancedHash_row67 = 0;

				// connection name:row67
				// source node:tDBInput_16 - inputs:(after_tFileInputExcel_35)
				// outputs:(row67,row67) | target node:tAdvancedHash_row67 - inputs:(row67)
				// outputs:()
				// linked node: tMap_51 - inputs:(row67,Dim_Reference_OSM_Opportunite_mobile)
				// outputs:(b)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row67 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row67Struct> tHash_Lookup_row67 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row67Struct>getLookup(matchingModeEnum_row67);

				globalMap.put("tHash_Lookup_row67", tHash_Lookup_row67);

				/**
				 * [tAdvancedHash_row67 begin ] stop
				 */

				/**
				 * [tDBInput_16 begin ] start
				 */

				ok_Hash.put("tDBInput_16", false);
				start_Hash.put("tDBInput_16", System.currentTimeMillis());

				currentComponent = "tDBInput_16";

				int tos_count_tDBInput_16 = 0;

				org.talend.designer.components.util.mssql.MSSqlGenerateTimestampUtil mssqlGTU_tDBInput_16 = org.talend.designer.components.util.mssql.MSSqlUtilFactory
						.getMSSqlGenerateTimestampUtil();

				java.util.List<String> talendToDBList_tDBInput_16 = new java.util.ArrayList();
				String[] talendToDBArray_tDBInput_16 = new String[] { "FLOAT", "NUMERIC", "NUMERIC IDENTITY", "DECIMAL",
						"DECIMAL IDENTITY", "REAL" };
				java.util.Collections.addAll(talendToDBList_tDBInput_16, talendToDBArray_tDBInput_16);
				int nb_line_tDBInput_16 = 0;
				java.sql.Connection conn_tDBInput_16 = null;
				String driverClass_tDBInput_16 = "net.sourceforge.jtds.jdbc.Driver";
				java.lang.Class jdbcclazz_tDBInput_16 = java.lang.Class.forName(driverClass_tDBInput_16);
				String dbUser_tDBInput_16 = "sa";

				final String decryptedPassword_tDBInput_16 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:SV7H5kH599XQOcHtIFC1fp85RqvQICKLpgND2NVefGUrVJX0Bzw=");

				String dbPwd_tDBInput_16 = decryptedPassword_tDBInput_16;

				String port_tDBInput_16 = "1433";
				String dbname_tDBInput_16 = "orange_DW__";
				String url_tDBInput_16 = "jdbc:jtds:sqlserver://" + "DESKTOP-6RL9E91";
				if (!"".equals(port_tDBInput_16)) {
					url_tDBInput_16 += ":" + "1433";
				}
				if (!"".equals(dbname_tDBInput_16)) {
					url_tDBInput_16 += "//" + "orange_DW__";
				}
				url_tDBInput_16 += ";appName=" + projectName + ";" + "";
				String dbschema_tDBInput_16 = "dbo";

				conn_tDBInput_16 = java.sql.DriverManager.getConnection(url_tDBInput_16, dbUser_tDBInput_16,
						dbPwd_tDBInput_16);

				java.sql.Statement stmt_tDBInput_16 = conn_tDBInput_16.createStatement();

				String dbquery_tDBInput_16 = "SELECT dbo.Dim_Reference_OSM.Dim_Reference_OSM_Opportunite_id,\n		dbo.Dim_Reference_OSM.Reference_OSM__Opportunite\nFROM	"
						+ "dbo.Dim_Reference_OSM";

				globalMap.put("tDBInput_16_QUERY", dbquery_tDBInput_16);
				java.sql.ResultSet rs_tDBInput_16 = null;

				try {
					rs_tDBInput_16 = stmt_tDBInput_16.executeQuery(dbquery_tDBInput_16);
					java.sql.ResultSetMetaData rsmd_tDBInput_16 = rs_tDBInput_16.getMetaData();
					int colQtyInRs_tDBInput_16 = rsmd_tDBInput_16.getColumnCount();

					String tmpContent_tDBInput_16 = null;

					while (rs_tDBInput_16.next()) {
						nb_line_tDBInput_16++;

						if (colQtyInRs_tDBInput_16 < 1) {
							row67.Dim_Reference_OSM_Opportunite_id = 0;
						} else {

							row67.Dim_Reference_OSM_Opportunite_id = rs_tDBInput_16.getInt(1);
							if (rs_tDBInput_16.wasNull()) {
								throw new RuntimeException("Null value in non-Nullable column");
							}
						}
						if (colQtyInRs_tDBInput_16 < 2) {
							row67.Reference_OSM__Opportunite = null;
						} else {

							tmpContent_tDBInput_16 = rs_tDBInput_16.getString(2);
							if (tmpContent_tDBInput_16 != null) {
								if (talendToDBList_tDBInput_16.contains(
										rsmd_tDBInput_16.getColumnTypeName(2).toUpperCase(java.util.Locale.ENGLISH))) {
									row67.Reference_OSM__Opportunite = FormatterUtils
											.formatUnwithE(tmpContent_tDBInput_16);
								} else {
									row67.Reference_OSM__Opportunite = tmpContent_tDBInput_16;
								}
							} else {
								row67.Reference_OSM__Opportunite = null;
							}
						}

						/**
						 * [tDBInput_16 begin ] stop
						 */

						/**
						 * [tDBInput_16 main ] start
						 */

						currentComponent = "tDBInput_16";

						tos_count_tDBInput_16++;

						/**
						 * [tDBInput_16 main ] stop
						 */

						/**
						 * [tDBInput_16 process_data_begin ] start
						 */

						currentComponent = "tDBInput_16";

						/**
						 * [tDBInput_16 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row67 main ] start
						 */

						currentComponent = "tAdvancedHash_row67";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row67"

							);
						}

						row67Struct row67_HashRow = new row67Struct();

						row67_HashRow.Dim_Reference_OSM_Opportunite_id = row67.Dim_Reference_OSM_Opportunite_id;

						row67_HashRow.Reference_OSM__Opportunite = row67.Reference_OSM__Opportunite;

						tHash_Lookup_row67.put(row67_HashRow);

						tos_count_tAdvancedHash_row67++;

						/**
						 * [tAdvancedHash_row67 main ] stop
						 */

						/**
						 * [tAdvancedHash_row67 process_data_begin ] start
						 */

						currentComponent = "tAdvancedHash_row67";

						/**
						 * [tAdvancedHash_row67 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row67 process_data_end ] start
						 */

						currentComponent = "tAdvancedHash_row67";

						/**
						 * [tAdvancedHash_row67 process_data_end ] stop
						 */

						/**
						 * [tDBInput_16 process_data_end ] start
						 */

						currentComponent = "tDBInput_16";

						/**
						 * [tDBInput_16 process_data_end ] stop
						 */

						/**
						 * [tDBInput_16 end ] start
						 */

						currentComponent = "tDBInput_16";

					}
				} finally {
					if (rs_tDBInput_16 != null) {
						rs_tDBInput_16.close();
					}
					if (stmt_tDBInput_16 != null) {
						stmt_tDBInput_16.close();
					}
					if (conn_tDBInput_16 != null && !conn_tDBInput_16.isClosed()) {

						conn_tDBInput_16.close();

						if ("com.mysql.cj.jdbc.Driver".equals((String) globalMap.get("driverClass_"))
								&& routines.system.BundleUtils.inOSGi()) {
							Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread")
									.getMethod("checkedShutdown").invoke(null, (Object[]) null);
						}

					}
				}
				globalMap.put("tDBInput_16_NB_LINE", nb_line_tDBInput_16);

				ok_Hash.put("tDBInput_16", true);
				end_Hash.put("tDBInput_16", System.currentTimeMillis());

				/**
				 * [tDBInput_16 end ] stop
				 */

				/**
				 * [tAdvancedHash_row67 end ] start
				 */

				currentComponent = "tAdvancedHash_row67";

				tHash_Lookup_row67.endPut();

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row67");
				}

				ok_Hash.put("tAdvancedHash_row67", true);
				end_Hash.put("tAdvancedHash_row67", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row67 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tDBInput_16 finally ] start
				 */

				currentComponent = "tDBInput_16";

				/**
				 * [tDBInput_16 finally ] stop
				 */

				/**
				 * [tAdvancedHash_row67 finally ] start
				 */

				currentComponent = "tAdvancedHash_row67";

				/**
				 * [tAdvancedHash_row67 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBInput_16_SUBPROCESS_STATE", 1);
	}

	public static class row69Struct implements routines.system.IPersistableComparableLookupRow<row69Struct> {
		final static byte[] commonByteArrayLock_ORANGE_tzydfvq = new byte[0];
		static byte[] commonByteArray_ORANGE_tzydfvq = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int type_engagement_id;

		public int getType_engagement_id() {
			return this.type_engagement_id;
		}

		public String Type_d_engagement__Opportunite;

		public String getType_d_engagement__Opportunite() {
			return this.Type_d_engagement__Opportunite;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.Type_d_engagement__Opportunite == null) ? 0
						: this.Type_d_engagement__Opportunite.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row69Struct other = (row69Struct) obj;

			if (this.Type_d_engagement__Opportunite == null) {
				if (other.Type_d_engagement__Opportunite != null)
					return false;

			} else if (!this.Type_d_engagement__Opportunite.equals(other.Type_d_engagement__Opportunite))

				return false;

			return true;
		}

		public void copyDataTo(row69Struct other) {

			other.type_engagement_id = this.type_engagement_id;
			other.Type_d_engagement__Opportunite = this.Type_d_engagement__Opportunite;

		}

		public void copyKeysDataTo(row69Struct other) {

			other.Type_d_engagement__Opportunite = this.Type_d_engagement__Opportunite;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_tzydfvq.length) {
					if (length < 1024 && commonByteArray_ORANGE_tzydfvq.length == 0) {
						commonByteArray_ORANGE_tzydfvq = new byte[1024];
					} else {
						commonByteArray_ORANGE_tzydfvq = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_tzydfvq, 0, length);
				strReturn = new String(commonByteArray_ORANGE_tzydfvq, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_tzydfvq.length) {
					if (length < 1024 && commonByteArray_ORANGE_tzydfvq.length == 0) {
						commonByteArray_ORANGE_tzydfvq = new byte[1024];
					} else {
						commonByteArray_ORANGE_tzydfvq = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_tzydfvq, 0, length);
				strReturn = new String(commonByteArray_ORANGE_tzydfvq, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readKeysData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ORANGE_tzydfvq) {

				try {

					int length = 0;

					this.Type_d_engagement__Opportunite = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_tzydfvq) {

				try {

					int length = 0;

					this.Type_d_engagement__Opportunite = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Type_d_engagement__Opportunite, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Type_d_engagement__Opportunite, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		/**
		 * Fill Values data by reading ObjectInputStream.
		 */
		public void readValuesData(DataInputStream dis, ObjectInputStream ois) {
			try {

				int length = 0;

				this.type_engagement_id = dis.readInt();

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

				this.type_engagement_id = objectIn.readInt();

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				dos.writeInt(this.type_engagement_id);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				objectOut.writeInt(this.type_engagement_id);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		public boolean supportMarshaller() {
			return true;
		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("type_engagement_id=" + String.valueOf(type_engagement_id));
			sb.append(",Type_d_engagement__Opportunite=" + Type_d_engagement__Opportunite);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row69Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.Type_d_engagement__Opportunite,
					other.Type_d_engagement__Opportunite);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tDBInput_17Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBInput_17_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row69Struct row69 = new row69Struct();

				/**
				 * [tAdvancedHash_row69 begin ] start
				 */

				ok_Hash.put("tAdvancedHash_row69", false);
				start_Hash.put("tAdvancedHash_row69", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_row69";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row69");
				}

				int tos_count_tAdvancedHash_row69 = 0;

				// connection name:row69
				// source node:tDBInput_17 - inputs:(after_tFileInputExcel_35)
				// outputs:(row69,row69) | target node:tAdvancedHash_row69 - inputs:(row69)
				// outputs:()
				// linked node: tMap_52 - inputs:(row69,Dim_type_engagement_mobile) outputs:(n)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row69 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row69Struct> tHash_Lookup_row69 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row69Struct>getLookup(matchingModeEnum_row69);

				globalMap.put("tHash_Lookup_row69", tHash_Lookup_row69);

				/**
				 * [tAdvancedHash_row69 begin ] stop
				 */

				/**
				 * [tDBInput_17 begin ] start
				 */

				ok_Hash.put("tDBInput_17", false);
				start_Hash.put("tDBInput_17", System.currentTimeMillis());

				currentComponent = "tDBInput_17";

				int tos_count_tDBInput_17 = 0;

				org.talend.designer.components.util.mssql.MSSqlGenerateTimestampUtil mssqlGTU_tDBInput_17 = org.talend.designer.components.util.mssql.MSSqlUtilFactory
						.getMSSqlGenerateTimestampUtil();

				java.util.List<String> talendToDBList_tDBInput_17 = new java.util.ArrayList();
				String[] talendToDBArray_tDBInput_17 = new String[] { "FLOAT", "NUMERIC", "NUMERIC IDENTITY", "DECIMAL",
						"DECIMAL IDENTITY", "REAL" };
				java.util.Collections.addAll(talendToDBList_tDBInput_17, talendToDBArray_tDBInput_17);
				int nb_line_tDBInput_17 = 0;
				java.sql.Connection conn_tDBInput_17 = null;
				String driverClass_tDBInput_17 = "net.sourceforge.jtds.jdbc.Driver";
				java.lang.Class jdbcclazz_tDBInput_17 = java.lang.Class.forName(driverClass_tDBInput_17);
				String dbUser_tDBInput_17 = "sa";

				final String decryptedPassword_tDBInput_17 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:+XZP7zYX9r4SVMH+77KHggNVE2LtpGWAuLRcd3/0eqmsPSfCiaQ=");

				String dbPwd_tDBInput_17 = decryptedPassword_tDBInput_17;

				String port_tDBInput_17 = "1433";
				String dbname_tDBInput_17 = "orange_DW__";
				String url_tDBInput_17 = "jdbc:jtds:sqlserver://" + "DESKTOP-6RL9E91";
				if (!"".equals(port_tDBInput_17)) {
					url_tDBInput_17 += ":" + "1433";
				}
				if (!"".equals(dbname_tDBInput_17)) {
					url_tDBInput_17 += "//" + "orange_DW__";
				}
				url_tDBInput_17 += ";appName=" + projectName + ";" + "";
				String dbschema_tDBInput_17 = "dbo";

				conn_tDBInput_17 = java.sql.DriverManager.getConnection(url_tDBInput_17, dbUser_tDBInput_17,
						dbPwd_tDBInput_17);

				java.sql.Statement stmt_tDBInput_17 = conn_tDBInput_17.createStatement();

				String dbquery_tDBInput_17 = "SELECT dbo.Dim_type_engagement.type_engagement_id,\n		dbo.Dim_type_engagement.Type_d_engagement__Opportunite\nFROM	dbo.Di"
						+ "m_type_engagement";

				globalMap.put("tDBInput_17_QUERY", dbquery_tDBInput_17);
				java.sql.ResultSet rs_tDBInput_17 = null;

				try {
					rs_tDBInput_17 = stmt_tDBInput_17.executeQuery(dbquery_tDBInput_17);
					java.sql.ResultSetMetaData rsmd_tDBInput_17 = rs_tDBInput_17.getMetaData();
					int colQtyInRs_tDBInput_17 = rsmd_tDBInput_17.getColumnCount();

					String tmpContent_tDBInput_17 = null;

					while (rs_tDBInput_17.next()) {
						nb_line_tDBInput_17++;

						if (colQtyInRs_tDBInput_17 < 1) {
							row69.type_engagement_id = 0;
						} else {

							row69.type_engagement_id = rs_tDBInput_17.getInt(1);
							if (rs_tDBInput_17.wasNull()) {
								throw new RuntimeException("Null value in non-Nullable column");
							}
						}
						if (colQtyInRs_tDBInput_17 < 2) {
							row69.Type_d_engagement__Opportunite = null;
						} else {

							tmpContent_tDBInput_17 = rs_tDBInput_17.getString(2);
							if (tmpContent_tDBInput_17 != null) {
								if (talendToDBList_tDBInput_17.contains(
										rsmd_tDBInput_17.getColumnTypeName(2).toUpperCase(java.util.Locale.ENGLISH))) {
									row69.Type_d_engagement__Opportunite = FormatterUtils
											.formatUnwithE(tmpContent_tDBInput_17);
								} else {
									row69.Type_d_engagement__Opportunite = tmpContent_tDBInput_17;
								}
							} else {
								row69.Type_d_engagement__Opportunite = null;
							}
						}

						/**
						 * [tDBInput_17 begin ] stop
						 */

						/**
						 * [tDBInput_17 main ] start
						 */

						currentComponent = "tDBInput_17";

						tos_count_tDBInput_17++;

						/**
						 * [tDBInput_17 main ] stop
						 */

						/**
						 * [tDBInput_17 process_data_begin ] start
						 */

						currentComponent = "tDBInput_17";

						/**
						 * [tDBInput_17 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row69 main ] start
						 */

						currentComponent = "tAdvancedHash_row69";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row69"

							);
						}

						row69Struct row69_HashRow = new row69Struct();

						row69_HashRow.type_engagement_id = row69.type_engagement_id;

						row69_HashRow.Type_d_engagement__Opportunite = row69.Type_d_engagement__Opportunite;

						tHash_Lookup_row69.put(row69_HashRow);

						tos_count_tAdvancedHash_row69++;

						/**
						 * [tAdvancedHash_row69 main ] stop
						 */

						/**
						 * [tAdvancedHash_row69 process_data_begin ] start
						 */

						currentComponent = "tAdvancedHash_row69";

						/**
						 * [tAdvancedHash_row69 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row69 process_data_end ] start
						 */

						currentComponent = "tAdvancedHash_row69";

						/**
						 * [tAdvancedHash_row69 process_data_end ] stop
						 */

						/**
						 * [tDBInput_17 process_data_end ] start
						 */

						currentComponent = "tDBInput_17";

						/**
						 * [tDBInput_17 process_data_end ] stop
						 */

						/**
						 * [tDBInput_17 end ] start
						 */

						currentComponent = "tDBInput_17";

					}
				} finally {
					if (rs_tDBInput_17 != null) {
						rs_tDBInput_17.close();
					}
					if (stmt_tDBInput_17 != null) {
						stmt_tDBInput_17.close();
					}
					if (conn_tDBInput_17 != null && !conn_tDBInput_17.isClosed()) {

						conn_tDBInput_17.close();

						if ("com.mysql.cj.jdbc.Driver".equals((String) globalMap.get("driverClass_"))
								&& routines.system.BundleUtils.inOSGi()) {
							Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread")
									.getMethod("checkedShutdown").invoke(null, (Object[]) null);
						}

					}
				}
				globalMap.put("tDBInput_17_NB_LINE", nb_line_tDBInput_17);

				ok_Hash.put("tDBInput_17", true);
				end_Hash.put("tDBInput_17", System.currentTimeMillis());

				/**
				 * [tDBInput_17 end ] stop
				 */

				/**
				 * [tAdvancedHash_row69 end ] start
				 */

				currentComponent = "tAdvancedHash_row69";

				tHash_Lookup_row69.endPut();

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row69");
				}

				ok_Hash.put("tAdvancedHash_row69", true);
				end_Hash.put("tAdvancedHash_row69", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row69 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tDBInput_17 finally ] start
				 */

				currentComponent = "tDBInput_17";

				/**
				 * [tDBInput_17 finally ] stop
				 */

				/**
				 * [tAdvancedHash_row69 finally ] start
				 */

				currentComponent = "tAdvancedHash_row69";

				/**
				 * [tAdvancedHash_row69 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBInput_17_SUBPROCESS_STATE", 1);
	}

	public static class row72Struct implements routines.system.IPersistableComparableLookupRow<row72Struct> {
		final static byte[] commonByteArrayLock_ORANGE_tzydfvq = new byte[0];
		static byte[] commonByteArray_ORANGE_tzydfvq = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int Extension_id;

		public int getExtension_id() {
			return this.Extension_id;
		}

		public String Extension__Opportunite;

		public String getExtension__Opportunite() {
			return this.Extension__Opportunite;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result
						+ ((this.Extension__Opportunite == null) ? 0 : this.Extension__Opportunite.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row72Struct other = (row72Struct) obj;

			if (this.Extension__Opportunite == null) {
				if (other.Extension__Opportunite != null)
					return false;

			} else if (!this.Extension__Opportunite.equals(other.Extension__Opportunite))

				return false;

			return true;
		}

		public void copyDataTo(row72Struct other) {

			other.Extension_id = this.Extension_id;
			other.Extension__Opportunite = this.Extension__Opportunite;

		}

		public void copyKeysDataTo(row72Struct other) {

			other.Extension__Opportunite = this.Extension__Opportunite;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_tzydfvq.length) {
					if (length < 1024 && commonByteArray_ORANGE_tzydfvq.length == 0) {
						commonByteArray_ORANGE_tzydfvq = new byte[1024];
					} else {
						commonByteArray_ORANGE_tzydfvq = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_tzydfvq, 0, length);
				strReturn = new String(commonByteArray_ORANGE_tzydfvq, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_tzydfvq.length) {
					if (length < 1024 && commonByteArray_ORANGE_tzydfvq.length == 0) {
						commonByteArray_ORANGE_tzydfvq = new byte[1024];
					} else {
						commonByteArray_ORANGE_tzydfvq = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_tzydfvq, 0, length);
				strReturn = new String(commonByteArray_ORANGE_tzydfvq, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readKeysData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ORANGE_tzydfvq) {

				try {

					int length = 0;

					this.Extension__Opportunite = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_tzydfvq) {

				try {

					int length = 0;

					this.Extension__Opportunite = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Extension__Opportunite, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Extension__Opportunite, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		/**
		 * Fill Values data by reading ObjectInputStream.
		 */
		public void readValuesData(DataInputStream dis, ObjectInputStream ois) {
			try {

				int length = 0;

				this.Extension_id = dis.readInt();

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

				this.Extension_id = objectIn.readInt();

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				dos.writeInt(this.Extension_id);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				objectOut.writeInt(this.Extension_id);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		public boolean supportMarshaller() {
			return true;
		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Extension_id=" + String.valueOf(Extension_id));
			sb.append(",Extension__Opportunite=" + Extension__Opportunite);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row72Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.Extension__Opportunite, other.Extension__Opportunite);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tDBInput_18Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBInput_18_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row72Struct row72 = new row72Struct();

				/**
				 * [tAdvancedHash_row72 begin ] start
				 */

				ok_Hash.put("tAdvancedHash_row72", false);
				start_Hash.put("tAdvancedHash_row72", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_row72";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row72");
				}

				int tos_count_tAdvancedHash_row72 = 0;

				// connection name:row72
				// source node:tDBInput_18 - inputs:(after_tFileInputExcel_35)
				// outputs:(row72,row72) | target node:tAdvancedHash_row72 - inputs:(row72)
				// outputs:()
				// linked node: tMap_53 - inputs:(row72,Extension_id) outputs:(q)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row72 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row72Struct> tHash_Lookup_row72 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row72Struct>getLookup(matchingModeEnum_row72);

				globalMap.put("tHash_Lookup_row72", tHash_Lookup_row72);

				/**
				 * [tAdvancedHash_row72 begin ] stop
				 */

				/**
				 * [tDBInput_18 begin ] start
				 */

				ok_Hash.put("tDBInput_18", false);
				start_Hash.put("tDBInput_18", System.currentTimeMillis());

				currentComponent = "tDBInput_18";

				int tos_count_tDBInput_18 = 0;

				org.talend.designer.components.util.mssql.MSSqlGenerateTimestampUtil mssqlGTU_tDBInput_18 = org.talend.designer.components.util.mssql.MSSqlUtilFactory
						.getMSSqlGenerateTimestampUtil();

				java.util.List<String> talendToDBList_tDBInput_18 = new java.util.ArrayList();
				String[] talendToDBArray_tDBInput_18 = new String[] { "FLOAT", "NUMERIC", "NUMERIC IDENTITY", "DECIMAL",
						"DECIMAL IDENTITY", "REAL" };
				java.util.Collections.addAll(talendToDBList_tDBInput_18, talendToDBArray_tDBInput_18);
				int nb_line_tDBInput_18 = 0;
				java.sql.Connection conn_tDBInput_18 = null;
				String driverClass_tDBInput_18 = "net.sourceforge.jtds.jdbc.Driver";
				java.lang.Class jdbcclazz_tDBInput_18 = java.lang.Class.forName(driverClass_tDBInput_18);
				String dbUser_tDBInput_18 = "sa";

				final String decryptedPassword_tDBInput_18 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:fEWvIEJLrP/tevLJ8cD4z/j9gYbvZNsDHq78episKcFtzxCqwvw=");

				String dbPwd_tDBInput_18 = decryptedPassword_tDBInput_18;

				String port_tDBInput_18 = "1433";
				String dbname_tDBInput_18 = "orange_DW__";
				String url_tDBInput_18 = "jdbc:jtds:sqlserver://" + "DESKTOP-6RL9E91";
				if (!"".equals(port_tDBInput_18)) {
					url_tDBInput_18 += ":" + "1433";
				}
				if (!"".equals(dbname_tDBInput_18)) {
					url_tDBInput_18 += "//" + "orange_DW__";
				}
				url_tDBInput_18 += ";appName=" + projectName + ";" + "";
				String dbschema_tDBInput_18 = "dbo";

				conn_tDBInput_18 = java.sql.DriverManager.getConnection(url_tDBInput_18, dbUser_tDBInput_18,
						dbPwd_tDBInput_18);

				java.sql.Statement stmt_tDBInput_18 = conn_tDBInput_18.createStatement();

				String dbquery_tDBInput_18 = "SELECT dbo.Dim_Extension_mobile.Extension_id,\n		dbo.Dim_Extension_mobile.Extension__Opportunite\nFROM	dbo.Dim_Extension_"
						+ "mobile";

				globalMap.put("tDBInput_18_QUERY", dbquery_tDBInput_18);
				java.sql.ResultSet rs_tDBInput_18 = null;

				try {
					rs_tDBInput_18 = stmt_tDBInput_18.executeQuery(dbquery_tDBInput_18);
					java.sql.ResultSetMetaData rsmd_tDBInput_18 = rs_tDBInput_18.getMetaData();
					int colQtyInRs_tDBInput_18 = rsmd_tDBInput_18.getColumnCount();

					String tmpContent_tDBInput_18 = null;

					while (rs_tDBInput_18.next()) {
						nb_line_tDBInput_18++;

						if (colQtyInRs_tDBInput_18 < 1) {
							row72.Extension_id = 0;
						} else {

							row72.Extension_id = rs_tDBInput_18.getInt(1);
							if (rs_tDBInput_18.wasNull()) {
								throw new RuntimeException("Null value in non-Nullable column");
							}
						}
						if (colQtyInRs_tDBInput_18 < 2) {
							row72.Extension__Opportunite = null;
						} else {

							tmpContent_tDBInput_18 = rs_tDBInput_18.getString(2);
							if (tmpContent_tDBInput_18 != null) {
								if (talendToDBList_tDBInput_18.contains(
										rsmd_tDBInput_18.getColumnTypeName(2).toUpperCase(java.util.Locale.ENGLISH))) {
									row72.Extension__Opportunite = FormatterUtils.formatUnwithE(tmpContent_tDBInput_18);
								} else {
									row72.Extension__Opportunite = tmpContent_tDBInput_18;
								}
							} else {
								row72.Extension__Opportunite = null;
							}
						}

						/**
						 * [tDBInput_18 begin ] stop
						 */

						/**
						 * [tDBInput_18 main ] start
						 */

						currentComponent = "tDBInput_18";

						tos_count_tDBInput_18++;

						/**
						 * [tDBInput_18 main ] stop
						 */

						/**
						 * [tDBInput_18 process_data_begin ] start
						 */

						currentComponent = "tDBInput_18";

						/**
						 * [tDBInput_18 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row72 main ] start
						 */

						currentComponent = "tAdvancedHash_row72";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row72"

							);
						}

						row72Struct row72_HashRow = new row72Struct();

						row72_HashRow.Extension_id = row72.Extension_id;

						row72_HashRow.Extension__Opportunite = row72.Extension__Opportunite;

						tHash_Lookup_row72.put(row72_HashRow);

						tos_count_tAdvancedHash_row72++;

						/**
						 * [tAdvancedHash_row72 main ] stop
						 */

						/**
						 * [tAdvancedHash_row72 process_data_begin ] start
						 */

						currentComponent = "tAdvancedHash_row72";

						/**
						 * [tAdvancedHash_row72 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row72 process_data_end ] start
						 */

						currentComponent = "tAdvancedHash_row72";

						/**
						 * [tAdvancedHash_row72 process_data_end ] stop
						 */

						/**
						 * [tDBInput_18 process_data_end ] start
						 */

						currentComponent = "tDBInput_18";

						/**
						 * [tDBInput_18 process_data_end ] stop
						 */

						/**
						 * [tDBInput_18 end ] start
						 */

						currentComponent = "tDBInput_18";

					}
				} finally {
					if (rs_tDBInput_18 != null) {
						rs_tDBInput_18.close();
					}
					if (stmt_tDBInput_18 != null) {
						stmt_tDBInput_18.close();
					}
					if (conn_tDBInput_18 != null && !conn_tDBInput_18.isClosed()) {

						conn_tDBInput_18.close();

						if ("com.mysql.cj.jdbc.Driver".equals((String) globalMap.get("driverClass_"))
								&& routines.system.BundleUtils.inOSGi()) {
							Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread")
									.getMethod("checkedShutdown").invoke(null, (Object[]) null);
						}

					}
				}
				globalMap.put("tDBInput_18_NB_LINE", nb_line_tDBInput_18);

				ok_Hash.put("tDBInput_18", true);
				end_Hash.put("tDBInput_18", System.currentTimeMillis());

				/**
				 * [tDBInput_18 end ] stop
				 */

				/**
				 * [tAdvancedHash_row72 end ] start
				 */

				currentComponent = "tAdvancedHash_row72";

				tHash_Lookup_row72.endPut();

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row72");
				}

				ok_Hash.put("tAdvancedHash_row72", true);
				end_Hash.put("tAdvancedHash_row72", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row72 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tDBInput_18 finally ] start
				 */

				currentComponent = "tDBInput_18";

				/**
				 * [tDBInput_18 finally ] stop
				 */

				/**
				 * [tAdvancedHash_row72 finally ] start
				 */

				currentComponent = "tAdvancedHash_row72";

				/**
				 * [tAdvancedHash_row72 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBInput_18_SUBPROCESS_STATE", 1);
	}

	public static class row64Struct implements routines.system.IPersistableRow<row64Struct> {
		final static byte[] commonByteArrayLock_ORANGE_tzydfvq = new byte[0];
		static byte[] commonByteArray_ORANGE_tzydfvq = new byte[0];

		public String Offre;

		public String getOffre() {
			return this.Offre;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_tzydfvq.length) {
					if (length < 1024 && commonByteArray_ORANGE_tzydfvq.length == 0) {
						commonByteArray_ORANGE_tzydfvq = new byte[1024];
					} else {
						commonByteArray_ORANGE_tzydfvq = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_tzydfvq, 0, length);
				strReturn = new String(commonByteArray_ORANGE_tzydfvq, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_tzydfvq.length) {
					if (length < 1024 && commonByteArray_ORANGE_tzydfvq.length == 0) {
						commonByteArray_ORANGE_tzydfvq = new byte[1024];
					} else {
						commonByteArray_ORANGE_tzydfvq = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_tzydfvq, 0, length);
				strReturn = new String(commonByteArray_ORANGE_tzydfvq, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ORANGE_tzydfvq) {

				try {

					int length = 0;

					this.Offre = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_tzydfvq) {

				try {

					int length = 0;

					this.Offre = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Offre, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Offre, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Offre=" + Offre);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row64Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class cStruct implements routines.system.IPersistableRow<cStruct> {
		final static byte[] commonByteArrayLock_ORANGE_tzydfvq = new byte[0];
		static byte[] commonByteArray_ORANGE_tzydfvq = new byte[0];

		public String Offre;

		public String getOffre() {
			return this.Offre;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_tzydfvq.length) {
					if (length < 1024 && commonByteArray_ORANGE_tzydfvq.length == 0) {
						commonByteArray_ORANGE_tzydfvq = new byte[1024];
					} else {
						commonByteArray_ORANGE_tzydfvq = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_tzydfvq, 0, length);
				strReturn = new String(commonByteArray_ORANGE_tzydfvq, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_tzydfvq.length) {
					if (length < 1024 && commonByteArray_ORANGE_tzydfvq.length == 0) {
						commonByteArray_ORANGE_tzydfvq = new byte[1024];
					} else {
						commonByteArray_ORANGE_tzydfvq = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_tzydfvq, 0, length);
				strReturn = new String(commonByteArray_ORANGE_tzydfvq, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ORANGE_tzydfvq) {

				try {

					int length = 0;

					this.Offre = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_tzydfvq) {

				try {

					int length = 0;

					this.Offre = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Offre, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Offre, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Offre=" + Offre);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(cStruct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row66Struct implements routines.system.IPersistableRow<row66Struct> {
		final static byte[] commonByteArrayLock_ORANGE_tzydfvq = new byte[0];
		static byte[] commonByteArray_ORANGE_tzydfvq = new byte[0];

		public String Nature_Offre;

		public String getNature_Offre() {
			return this.Nature_Offre;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_tzydfvq.length) {
					if (length < 1024 && commonByteArray_ORANGE_tzydfvq.length == 0) {
						commonByteArray_ORANGE_tzydfvq = new byte[1024];
					} else {
						commonByteArray_ORANGE_tzydfvq = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_tzydfvq, 0, length);
				strReturn = new String(commonByteArray_ORANGE_tzydfvq, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_tzydfvq.length) {
					if (length < 1024 && commonByteArray_ORANGE_tzydfvq.length == 0) {
						commonByteArray_ORANGE_tzydfvq = new byte[1024];
					} else {
						commonByteArray_ORANGE_tzydfvq = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_tzydfvq, 0, length);
				strReturn = new String(commonByteArray_ORANGE_tzydfvq, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ORANGE_tzydfvq) {

				try {

					int length = 0;

					this.Nature_Offre = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_tzydfvq) {

				try {

					int length = 0;

					this.Nature_Offre = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Nature_Offre, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Nature_Offre, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Nature_Offre=" + Nature_Offre);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row66Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class vStruct implements routines.system.IPersistableRow<vStruct> {
		final static byte[] commonByteArrayLock_ORANGE_tzydfvq = new byte[0];
		static byte[] commonByteArray_ORANGE_tzydfvq = new byte[0];

		public String Nature_Offre;

		public String getNature_Offre() {
			return this.Nature_Offre;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_tzydfvq.length) {
					if (length < 1024 && commonByteArray_ORANGE_tzydfvq.length == 0) {
						commonByteArray_ORANGE_tzydfvq = new byte[1024];
					} else {
						commonByteArray_ORANGE_tzydfvq = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_tzydfvq, 0, length);
				strReturn = new String(commonByteArray_ORANGE_tzydfvq, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_tzydfvq.length) {
					if (length < 1024 && commonByteArray_ORANGE_tzydfvq.length == 0) {
						commonByteArray_ORANGE_tzydfvq = new byte[1024];
					} else {
						commonByteArray_ORANGE_tzydfvq = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_tzydfvq, 0, length);
				strReturn = new String(commonByteArray_ORANGE_tzydfvq, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ORANGE_tzydfvq) {

				try {

					int length = 0;

					this.Nature_Offre = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_tzydfvq) {

				try {

					int length = 0;

					this.Nature_Offre = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Nature_Offre, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Nature_Offre, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Nature_Offre=" + Nature_Offre);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(vStruct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row71Struct implements routines.system.IPersistableRow<row71Struct> {
		final static byte[] commonByteArrayLock_ORANGE_tzydfvq = new byte[0];
		static byte[] commonByteArray_ORANGE_tzydfvq = new byte[0];

		public String Extension__Opportunite;

		public String getExtension__Opportunite() {
			return this.Extension__Opportunite;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_tzydfvq.length) {
					if (length < 1024 && commonByteArray_ORANGE_tzydfvq.length == 0) {
						commonByteArray_ORANGE_tzydfvq = new byte[1024];
					} else {
						commonByteArray_ORANGE_tzydfvq = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_tzydfvq, 0, length);
				strReturn = new String(commonByteArray_ORANGE_tzydfvq, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_tzydfvq.length) {
					if (length < 1024 && commonByteArray_ORANGE_tzydfvq.length == 0) {
						commonByteArray_ORANGE_tzydfvq = new byte[1024];
					} else {
						commonByteArray_ORANGE_tzydfvq = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_tzydfvq, 0, length);
				strReturn = new String(commonByteArray_ORANGE_tzydfvq, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ORANGE_tzydfvq) {

				try {

					int length = 0;

					this.Extension__Opportunite = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_tzydfvq) {

				try {

					int length = 0;

					this.Extension__Opportunite = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Extension__Opportunite, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Extension__Opportunite, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Extension__Opportunite=" + Extension__Opportunite);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row71Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class qStruct implements routines.system.IPersistableRow<qStruct> {
		final static byte[] commonByteArrayLock_ORANGE_tzydfvq = new byte[0];
		static byte[] commonByteArray_ORANGE_tzydfvq = new byte[0];

		public String Extension__Opportunite;

		public String getExtension__Opportunite() {
			return this.Extension__Opportunite;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_tzydfvq.length) {
					if (length < 1024 && commonByteArray_ORANGE_tzydfvq.length == 0) {
						commonByteArray_ORANGE_tzydfvq = new byte[1024];
					} else {
						commonByteArray_ORANGE_tzydfvq = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_tzydfvq, 0, length);
				strReturn = new String(commonByteArray_ORANGE_tzydfvq, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_tzydfvq.length) {
					if (length < 1024 && commonByteArray_ORANGE_tzydfvq.length == 0) {
						commonByteArray_ORANGE_tzydfvq = new byte[1024];
					} else {
						commonByteArray_ORANGE_tzydfvq = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_tzydfvq, 0, length);
				strReturn = new String(commonByteArray_ORANGE_tzydfvq, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ORANGE_tzydfvq) {

				try {

					int length = 0;

					this.Extension__Opportunite = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_tzydfvq) {

				try {

					int length = 0;

					this.Extension__Opportunite = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Extension__Opportunite, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Extension__Opportunite, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Extension__Opportunite=" + Extension__Opportunite);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(qStruct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row70Struct implements routines.system.IPersistableRow<row70Struct> {
		final static byte[] commonByteArrayLock_ORANGE_tzydfvq = new byte[0];
		static byte[] commonByteArray_ORANGE_tzydfvq = new byte[0];

		public String Type_d_engagement__Opportunite;

		public String getType_d_engagement__Opportunite() {
			return this.Type_d_engagement__Opportunite;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_tzydfvq.length) {
					if (length < 1024 && commonByteArray_ORANGE_tzydfvq.length == 0) {
						commonByteArray_ORANGE_tzydfvq = new byte[1024];
					} else {
						commonByteArray_ORANGE_tzydfvq = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_tzydfvq, 0, length);
				strReturn = new String(commonByteArray_ORANGE_tzydfvq, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_tzydfvq.length) {
					if (length < 1024 && commonByteArray_ORANGE_tzydfvq.length == 0) {
						commonByteArray_ORANGE_tzydfvq = new byte[1024];
					} else {
						commonByteArray_ORANGE_tzydfvq = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_tzydfvq, 0, length);
				strReturn = new String(commonByteArray_ORANGE_tzydfvq, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ORANGE_tzydfvq) {

				try {

					int length = 0;

					this.Type_d_engagement__Opportunite = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_tzydfvq) {

				try {

					int length = 0;

					this.Type_d_engagement__Opportunite = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Type_d_engagement__Opportunite, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Type_d_engagement__Opportunite, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Type_d_engagement__Opportunite=" + Type_d_engagement__Opportunite);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row70Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class nStruct implements routines.system.IPersistableRow<nStruct> {
		final static byte[] commonByteArrayLock_ORANGE_tzydfvq = new byte[0];
		static byte[] commonByteArray_ORANGE_tzydfvq = new byte[0];

		public String Type_d_engagement__Opportunite;

		public String getType_d_engagement__Opportunite() {
			return this.Type_d_engagement__Opportunite;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_tzydfvq.length) {
					if (length < 1024 && commonByteArray_ORANGE_tzydfvq.length == 0) {
						commonByteArray_ORANGE_tzydfvq = new byte[1024];
					} else {
						commonByteArray_ORANGE_tzydfvq = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_tzydfvq, 0, length);
				strReturn = new String(commonByteArray_ORANGE_tzydfvq, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_tzydfvq.length) {
					if (length < 1024 && commonByteArray_ORANGE_tzydfvq.length == 0) {
						commonByteArray_ORANGE_tzydfvq = new byte[1024];
					} else {
						commonByteArray_ORANGE_tzydfvq = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_tzydfvq, 0, length);
				strReturn = new String(commonByteArray_ORANGE_tzydfvq, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ORANGE_tzydfvq) {

				try {

					int length = 0;

					this.Type_d_engagement__Opportunite = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_tzydfvq) {

				try {

					int length = 0;

					this.Type_d_engagement__Opportunite = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Type_d_engagement__Opportunite, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Type_d_engagement__Opportunite, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Type_d_engagement__Opportunite=" + Type_d_engagement__Opportunite);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(nStruct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row68Struct implements routines.system.IPersistableRow<row68Struct> {
		final static byte[] commonByteArrayLock_ORANGE_tzydfvq = new byte[0];
		static byte[] commonByteArray_ORANGE_tzydfvq = new byte[0];

		public String Reference_OSM__Opportunite;

		public String getReference_OSM__Opportunite() {
			return this.Reference_OSM__Opportunite;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_tzydfvq.length) {
					if (length < 1024 && commonByteArray_ORANGE_tzydfvq.length == 0) {
						commonByteArray_ORANGE_tzydfvq = new byte[1024];
					} else {
						commonByteArray_ORANGE_tzydfvq = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_tzydfvq, 0, length);
				strReturn = new String(commonByteArray_ORANGE_tzydfvq, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_tzydfvq.length) {
					if (length < 1024 && commonByteArray_ORANGE_tzydfvq.length == 0) {
						commonByteArray_ORANGE_tzydfvq = new byte[1024];
					} else {
						commonByteArray_ORANGE_tzydfvq = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_tzydfvq, 0, length);
				strReturn = new String(commonByteArray_ORANGE_tzydfvq, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ORANGE_tzydfvq) {

				try {

					int length = 0;

					this.Reference_OSM__Opportunite = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_tzydfvq) {

				try {

					int length = 0;

					this.Reference_OSM__Opportunite = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Reference_OSM__Opportunite, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Reference_OSM__Opportunite, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Reference_OSM__Opportunite=" + Reference_OSM__Opportunite);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row68Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class bStruct implements routines.system.IPersistableRow<bStruct> {
		final static byte[] commonByteArrayLock_ORANGE_tzydfvq = new byte[0];
		static byte[] commonByteArray_ORANGE_tzydfvq = new byte[0];

		public String Reference_OSM__Opportunite;

		public String getReference_OSM__Opportunite() {
			return this.Reference_OSM__Opportunite;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_tzydfvq.length) {
					if (length < 1024 && commonByteArray_ORANGE_tzydfvq.length == 0) {
						commonByteArray_ORANGE_tzydfvq = new byte[1024];
					} else {
						commonByteArray_ORANGE_tzydfvq = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_tzydfvq, 0, length);
				strReturn = new String(commonByteArray_ORANGE_tzydfvq, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_tzydfvq.length) {
					if (length < 1024 && commonByteArray_ORANGE_tzydfvq.length == 0) {
						commonByteArray_ORANGE_tzydfvq = new byte[1024];
					} else {
						commonByteArray_ORANGE_tzydfvq = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_tzydfvq, 0, length);
				strReturn = new String(commonByteArray_ORANGE_tzydfvq, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ORANGE_tzydfvq) {

				try {

					int length = 0;

					this.Reference_OSM__Opportunite = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_tzydfvq) {

				try {

					int length = 0;

					this.Reference_OSM__Opportunite = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Reference_OSM__Opportunite, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Reference_OSM__Opportunite, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Reference_OSM__Opportunite=" + Reference_OSM__Opportunite);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(bStruct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class Dim_Reference_OSM_Opportunite_mobileStruct
			implements routines.system.IPersistableRow<Dim_Reference_OSM_Opportunite_mobileStruct> {
		final static byte[] commonByteArrayLock_ORANGE_tzydfvq = new byte[0];
		static byte[] commonByteArray_ORANGE_tzydfvq = new byte[0];

		public String Reference_OSM__Opportunite;

		public String getReference_OSM__Opportunite() {
			return this.Reference_OSM__Opportunite;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_tzydfvq.length) {
					if (length < 1024 && commonByteArray_ORANGE_tzydfvq.length == 0) {
						commonByteArray_ORANGE_tzydfvq = new byte[1024];
					} else {
						commonByteArray_ORANGE_tzydfvq = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_tzydfvq, 0, length);
				strReturn = new String(commonByteArray_ORANGE_tzydfvq, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_tzydfvq.length) {
					if (length < 1024 && commonByteArray_ORANGE_tzydfvq.length == 0) {
						commonByteArray_ORANGE_tzydfvq = new byte[1024];
					} else {
						commonByteArray_ORANGE_tzydfvq = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_tzydfvq, 0, length);
				strReturn = new String(commonByteArray_ORANGE_tzydfvq, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ORANGE_tzydfvq) {

				try {

					int length = 0;

					this.Reference_OSM__Opportunite = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_tzydfvq) {

				try {

					int length = 0;

					this.Reference_OSM__Opportunite = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Reference_OSM__Opportunite, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Reference_OSM__Opportunite, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Reference_OSM__Opportunite=" + Reference_OSM__Opportunite);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(Dim_Reference_OSM_Opportunite_mobileStruct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class Dim_type_engagement_mobileStruct
			implements routines.system.IPersistableRow<Dim_type_engagement_mobileStruct> {
		final static byte[] commonByteArrayLock_ORANGE_tzydfvq = new byte[0];
		static byte[] commonByteArray_ORANGE_tzydfvq = new byte[0];

		public String Type_d_engagement__Opportunite;

		public String getType_d_engagement__Opportunite() {
			return this.Type_d_engagement__Opportunite;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_tzydfvq.length) {
					if (length < 1024 && commonByteArray_ORANGE_tzydfvq.length == 0) {
						commonByteArray_ORANGE_tzydfvq = new byte[1024];
					} else {
						commonByteArray_ORANGE_tzydfvq = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_tzydfvq, 0, length);
				strReturn = new String(commonByteArray_ORANGE_tzydfvq, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_tzydfvq.length) {
					if (length < 1024 && commonByteArray_ORANGE_tzydfvq.length == 0) {
						commonByteArray_ORANGE_tzydfvq = new byte[1024];
					} else {
						commonByteArray_ORANGE_tzydfvq = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_tzydfvq, 0, length);
				strReturn = new String(commonByteArray_ORANGE_tzydfvq, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ORANGE_tzydfvq) {

				try {

					int length = 0;

					this.Type_d_engagement__Opportunite = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_tzydfvq) {

				try {

					int length = 0;

					this.Type_d_engagement__Opportunite = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Type_d_engagement__Opportunite, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Type_d_engagement__Opportunite, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Type_d_engagement__Opportunite=" + Type_d_engagement__Opportunite);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(Dim_type_engagement_mobileStruct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class Extension_idStruct implements routines.system.IPersistableRow<Extension_idStruct> {
		final static byte[] commonByteArrayLock_ORANGE_tzydfvq = new byte[0];
		static byte[] commonByteArray_ORANGE_tzydfvq = new byte[0];

		public String Extension__Opportunite;

		public String getExtension__Opportunite() {
			return this.Extension__Opportunite;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_tzydfvq.length) {
					if (length < 1024 && commonByteArray_ORANGE_tzydfvq.length == 0) {
						commonByteArray_ORANGE_tzydfvq = new byte[1024];
					} else {
						commonByteArray_ORANGE_tzydfvq = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_tzydfvq, 0, length);
				strReturn = new String(commonByteArray_ORANGE_tzydfvq, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_tzydfvq.length) {
					if (length < 1024 && commonByteArray_ORANGE_tzydfvq.length == 0) {
						commonByteArray_ORANGE_tzydfvq = new byte[1024];
					} else {
						commonByteArray_ORANGE_tzydfvq = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_tzydfvq, 0, length);
				strReturn = new String(commonByteArray_ORANGE_tzydfvq, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ORANGE_tzydfvq) {

				try {

					int length = 0;

					this.Extension__Opportunite = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_tzydfvq) {

				try {

					int length = 0;

					this.Extension__Opportunite = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Extension__Opportunite, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Extension__Opportunite, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Extension__Opportunite=" + Extension__Opportunite);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(Extension_idStruct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class Dim_nature_offre_mobileStruct
			implements routines.system.IPersistableRow<Dim_nature_offre_mobileStruct> {
		final static byte[] commonByteArrayLock_ORANGE_tzydfvq = new byte[0];
		static byte[] commonByteArray_ORANGE_tzydfvq = new byte[0];

		public String Nature_Offre;

		public String getNature_Offre() {
			return this.Nature_Offre;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_tzydfvq.length) {
					if (length < 1024 && commonByteArray_ORANGE_tzydfvq.length == 0) {
						commonByteArray_ORANGE_tzydfvq = new byte[1024];
					} else {
						commonByteArray_ORANGE_tzydfvq = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_tzydfvq, 0, length);
				strReturn = new String(commonByteArray_ORANGE_tzydfvq, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_tzydfvq.length) {
					if (length < 1024 && commonByteArray_ORANGE_tzydfvq.length == 0) {
						commonByteArray_ORANGE_tzydfvq = new byte[1024];
					} else {
						commonByteArray_ORANGE_tzydfvq = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_tzydfvq, 0, length);
				strReturn = new String(commonByteArray_ORANGE_tzydfvq, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ORANGE_tzydfvq) {

				try {

					int length = 0;

					this.Nature_Offre = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_tzydfvq) {

				try {

					int length = 0;

					this.Nature_Offre = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Nature_Offre, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Nature_Offre, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Nature_Offre=" + Nature_Offre);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(Dim_nature_offre_mobileStruct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class Dim_offre_mobileStruct implements routines.system.IPersistableRow<Dim_offre_mobileStruct> {
		final static byte[] commonByteArrayLock_ORANGE_tzydfvq = new byte[0];
		static byte[] commonByteArray_ORANGE_tzydfvq = new byte[0];

		public String Offre;

		public String getOffre() {
			return this.Offre;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_tzydfvq.length) {
					if (length < 1024 && commonByteArray_ORANGE_tzydfvq.length == 0) {
						commonByteArray_ORANGE_tzydfvq = new byte[1024];
					} else {
						commonByteArray_ORANGE_tzydfvq = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_tzydfvq, 0, length);
				strReturn = new String(commonByteArray_ORANGE_tzydfvq, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_tzydfvq.length) {
					if (length < 1024 && commonByteArray_ORANGE_tzydfvq.length == 0) {
						commonByteArray_ORANGE_tzydfvq = new byte[1024];
					} else {
						commonByteArray_ORANGE_tzydfvq = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_tzydfvq, 0, length);
				strReturn = new String(commonByteArray_ORANGE_tzydfvq, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ORANGE_tzydfvq) {

				try {

					int length = 0;

					this.Offre = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_tzydfvq) {

				try {

					int length = 0;

					this.Offre = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Offre, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Offre, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Offre=" + Offre);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(Dim_offre_mobileStruct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row58Struct implements routines.system.IPersistableRow<row58Struct> {
		final static byte[] commonByteArrayLock_ORANGE_tzydfvq = new byte[0];
		static byte[] commonByteArray_ORANGE_tzydfvq = new byte[0];

		public String Reference_OSM_mere__Opportunite;

		public String getReference_OSM_mere__Opportunite() {
			return this.Reference_OSM_mere__Opportunite;
		}

		public String Reference_OSM__Opportunite;

		public String getReference_OSM__Opportunite() {
			return this.Reference_OSM__Opportunite;
		}

		public String Client_Prospect__Opportunite;

		public String getClient_Prospect__Opportunite() {
			return this.Client_Prospect__Opportunite;
		}

		public String Offre;

		public String getOffre() {
			return this.Offre;
		}

		public String Nature_Offre;

		public String getNature_Offre() {
			return this.Nature_Offre;
		}

		public String Produit_existant;

		public String getProduit_existant() {
			return this.Produit_existant;
		}

		public String Nature_produit__Produit_existant;

		public String getNature_produit__Produit_existant() {
			return this.Nature_produit__Produit_existant;
		}

		public Integer Quantite;

		public Integer getQuantite() {
			return this.Quantite;
		}

		public String Prix_unitaire;

		public String getPrix_unitaire() {
			return this.Prix_unitaire;
		}

		public String Total_final;

		public String getTotal_final() {
			return this.Total_final;
		}

		public Float Remise______Opportunite;

		public Float getRemise______Opportunite() {
			return this.Remise______Opportunite;
		}

		public Float Total_remise_HT;

		public Float getTotal_remise_HT() {
			return this.Total_remise_HT;
		}

		public String Extension__Opportunite;

		public String getExtension__Opportunite() {
			return this.Extension__Opportunite;
		}

		public String Budget_a_valider__Opportunite;

		public String getBudget_a_valider__Opportunite() {
			return this.Budget_a_valider__Opportunite;
		}

		public String Budget_a_valider_Extension__Opportunite;

		public String getBudget_a_valider_Extension__Opportunite() {
			return this.Budget_a_valider_Extension__Opportunite;
		}

		public String Duree_d_engagement__Opportunite;

		public String getDuree_d_engagement__Opportunite() {
			return this.Duree_d_engagement__Opportunite;
		}

		public String Type_d_engagement__Opportunite;

		public String getType_d_engagement__Opportunite() {
			return this.Type_d_engagement__Opportunite;
		}

		public String Demandeur__Opportunite;

		public String getDemandeur__Opportunite() {
			return this.Demandeur__Opportunite;
		}

		public String Equipe;

		public String getEquipe() {
			return this.Equipe;
		}

		public String Cree_le__Opportunite;

		public String getCree_le__Opportunite() {
			return this.Cree_le__Opportunite;
		}

		public Integer Mois_de_creation;

		public Integer getMois_de_creation() {
			return this.Mois_de_creation;
		}

		public Integer Annee_de_creation;

		public Integer getAnnee_de_creation() {
			return this.Annee_de_creation;
		}

		public String Mois_et_annee_de_creation;

		public String getMois_et_annee_de_creation() {
			return this.Mois_et_annee_de_creation;
		}

		public String Raison_de_statut_OSM__Opportunite;

		public String getRaison_de_statut_OSM__Opportunite() {
			return this.Raison_de_statut_OSM__Opportunite;
		}

		public String Statut_OSM__Opportunite;

		public String getStatut_OSM__Opportunite() {
			return this.Statut_OSM__Opportunite;
		}

		public String Statut_activation__Commande;

		public String getStatut_activation__Commande() {
			return this.Statut_activation__Commande;
		}

		public String Date_d_activation__Commande;

		public String getDate_d_activation__Commande() {
			return this.Date_d_activation__Commande;
		}

		public Integer Mois_d_activation;

		public Integer getMois_d_activation() {
			return this.Mois_d_activation;
		}

		public String Staut_Final;

		public String getStaut_Final() {
			return this.Staut_Final;
		}

		public String Gouvernorat;

		public String getGouvernorat() {
			return this.Gouvernorat;
		}

		public String Phase_de_l_opportunite__Opportunite;

		public String getPhase_de_l_opportunite__Opportunite() {
			return this.Phase_de_l_opportunite__Opportunite;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_tzydfvq.length) {
					if (length < 1024 && commonByteArray_ORANGE_tzydfvq.length == 0) {
						commonByteArray_ORANGE_tzydfvq = new byte[1024];
					} else {
						commonByteArray_ORANGE_tzydfvq = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_tzydfvq, 0, length);
				strReturn = new String(commonByteArray_ORANGE_tzydfvq, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_tzydfvq.length) {
					if (length < 1024 && commonByteArray_ORANGE_tzydfvq.length == 0) {
						commonByteArray_ORANGE_tzydfvq = new byte[1024];
					} else {
						commonByteArray_ORANGE_tzydfvq = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_tzydfvq, 0, length);
				strReturn = new String(commonByteArray_ORANGE_tzydfvq, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ORANGE_tzydfvq) {

				try {

					int length = 0;

					this.Reference_OSM_mere__Opportunite = readString(dis);

					this.Reference_OSM__Opportunite = readString(dis);

					this.Client_Prospect__Opportunite = readString(dis);

					this.Offre = readString(dis);

					this.Nature_Offre = readString(dis);

					this.Produit_existant = readString(dis);

					this.Nature_produit__Produit_existant = readString(dis);

					this.Quantite = readInteger(dis);

					this.Prix_unitaire = readString(dis);

					this.Total_final = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Remise______Opportunite = null;
					} else {
						this.Remise______Opportunite = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Total_remise_HT = null;
					} else {
						this.Total_remise_HT = dis.readFloat();
					}

					this.Extension__Opportunite = readString(dis);

					this.Budget_a_valider__Opportunite = readString(dis);

					this.Budget_a_valider_Extension__Opportunite = readString(dis);

					this.Duree_d_engagement__Opportunite = readString(dis);

					this.Type_d_engagement__Opportunite = readString(dis);

					this.Demandeur__Opportunite = readString(dis);

					this.Equipe = readString(dis);

					this.Cree_le__Opportunite = readString(dis);

					this.Mois_de_creation = readInteger(dis);

					this.Annee_de_creation = readInteger(dis);

					this.Mois_et_annee_de_creation = readString(dis);

					this.Raison_de_statut_OSM__Opportunite = readString(dis);

					this.Statut_OSM__Opportunite = readString(dis);

					this.Statut_activation__Commande = readString(dis);

					this.Date_d_activation__Commande = readString(dis);

					this.Mois_d_activation = readInteger(dis);

					this.Staut_Final = readString(dis);

					this.Gouvernorat = readString(dis);

					this.Phase_de_l_opportunite__Opportunite = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_tzydfvq) {

				try {

					int length = 0;

					this.Reference_OSM_mere__Opportunite = readString(dis);

					this.Reference_OSM__Opportunite = readString(dis);

					this.Client_Prospect__Opportunite = readString(dis);

					this.Offre = readString(dis);

					this.Nature_Offre = readString(dis);

					this.Produit_existant = readString(dis);

					this.Nature_produit__Produit_existant = readString(dis);

					this.Quantite = readInteger(dis);

					this.Prix_unitaire = readString(dis);

					this.Total_final = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Remise______Opportunite = null;
					} else {
						this.Remise______Opportunite = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Total_remise_HT = null;
					} else {
						this.Total_remise_HT = dis.readFloat();
					}

					this.Extension__Opportunite = readString(dis);

					this.Budget_a_valider__Opportunite = readString(dis);

					this.Budget_a_valider_Extension__Opportunite = readString(dis);

					this.Duree_d_engagement__Opportunite = readString(dis);

					this.Type_d_engagement__Opportunite = readString(dis);

					this.Demandeur__Opportunite = readString(dis);

					this.Equipe = readString(dis);

					this.Cree_le__Opportunite = readString(dis);

					this.Mois_de_creation = readInteger(dis);

					this.Annee_de_creation = readInteger(dis);

					this.Mois_et_annee_de_creation = readString(dis);

					this.Raison_de_statut_OSM__Opportunite = readString(dis);

					this.Statut_OSM__Opportunite = readString(dis);

					this.Statut_activation__Commande = readString(dis);

					this.Date_d_activation__Commande = readString(dis);

					this.Mois_d_activation = readInteger(dis);

					this.Staut_Final = readString(dis);

					this.Gouvernorat = readString(dis);

					this.Phase_de_l_opportunite__Opportunite = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Reference_OSM_mere__Opportunite, dos);

				// String

				writeString(this.Reference_OSM__Opportunite, dos);

				// String

				writeString(this.Client_Prospect__Opportunite, dos);

				// String

				writeString(this.Offre, dos);

				// String

				writeString(this.Nature_Offre, dos);

				// String

				writeString(this.Produit_existant, dos);

				// String

				writeString(this.Nature_produit__Produit_existant, dos);

				// Integer

				writeInteger(this.Quantite, dos);

				// String

				writeString(this.Prix_unitaire, dos);

				// String

				writeString(this.Total_final, dos);

				// Float

				if (this.Remise______Opportunite == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Remise______Opportunite);
				}

				// Float

				if (this.Total_remise_HT == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Total_remise_HT);
				}

				// String

				writeString(this.Extension__Opportunite, dos);

				// String

				writeString(this.Budget_a_valider__Opportunite, dos);

				// String

				writeString(this.Budget_a_valider_Extension__Opportunite, dos);

				// String

				writeString(this.Duree_d_engagement__Opportunite, dos);

				// String

				writeString(this.Type_d_engagement__Opportunite, dos);

				// String

				writeString(this.Demandeur__Opportunite, dos);

				// String

				writeString(this.Equipe, dos);

				// String

				writeString(this.Cree_le__Opportunite, dos);

				// Integer

				writeInteger(this.Mois_de_creation, dos);

				// Integer

				writeInteger(this.Annee_de_creation, dos);

				// String

				writeString(this.Mois_et_annee_de_creation, dos);

				// String

				writeString(this.Raison_de_statut_OSM__Opportunite, dos);

				// String

				writeString(this.Statut_OSM__Opportunite, dos);

				// String

				writeString(this.Statut_activation__Commande, dos);

				// String

				writeString(this.Date_d_activation__Commande, dos);

				// Integer

				writeInteger(this.Mois_d_activation, dos);

				// String

				writeString(this.Staut_Final, dos);

				// String

				writeString(this.Gouvernorat, dos);

				// String

				writeString(this.Phase_de_l_opportunite__Opportunite, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Reference_OSM_mere__Opportunite, dos);

				// String

				writeString(this.Reference_OSM__Opportunite, dos);

				// String

				writeString(this.Client_Prospect__Opportunite, dos);

				// String

				writeString(this.Offre, dos);

				// String

				writeString(this.Nature_Offre, dos);

				// String

				writeString(this.Produit_existant, dos);

				// String

				writeString(this.Nature_produit__Produit_existant, dos);

				// Integer

				writeInteger(this.Quantite, dos);

				// String

				writeString(this.Prix_unitaire, dos);

				// String

				writeString(this.Total_final, dos);

				// Float

				if (this.Remise______Opportunite == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Remise______Opportunite);
				}

				// Float

				if (this.Total_remise_HT == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Total_remise_HT);
				}

				// String

				writeString(this.Extension__Opportunite, dos);

				// String

				writeString(this.Budget_a_valider__Opportunite, dos);

				// String

				writeString(this.Budget_a_valider_Extension__Opportunite, dos);

				// String

				writeString(this.Duree_d_engagement__Opportunite, dos);

				// String

				writeString(this.Type_d_engagement__Opportunite, dos);

				// String

				writeString(this.Demandeur__Opportunite, dos);

				// String

				writeString(this.Equipe, dos);

				// String

				writeString(this.Cree_le__Opportunite, dos);

				// Integer

				writeInteger(this.Mois_de_creation, dos);

				// Integer

				writeInteger(this.Annee_de_creation, dos);

				// String

				writeString(this.Mois_et_annee_de_creation, dos);

				// String

				writeString(this.Raison_de_statut_OSM__Opportunite, dos);

				// String

				writeString(this.Statut_OSM__Opportunite, dos);

				// String

				writeString(this.Statut_activation__Commande, dos);

				// String

				writeString(this.Date_d_activation__Commande, dos);

				// Integer

				writeInteger(this.Mois_d_activation, dos);

				// String

				writeString(this.Staut_Final, dos);

				// String

				writeString(this.Gouvernorat, dos);

				// String

				writeString(this.Phase_de_l_opportunite__Opportunite, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Reference_OSM_mere__Opportunite=" + Reference_OSM_mere__Opportunite);
			sb.append(",Reference_OSM__Opportunite=" + Reference_OSM__Opportunite);
			sb.append(",Client_Prospect__Opportunite=" + Client_Prospect__Opportunite);
			sb.append(",Offre=" + Offre);
			sb.append(",Nature_Offre=" + Nature_Offre);
			sb.append(",Produit_existant=" + Produit_existant);
			sb.append(",Nature_produit__Produit_existant=" + Nature_produit__Produit_existant);
			sb.append(",Quantite=" + String.valueOf(Quantite));
			sb.append(",Prix_unitaire=" + Prix_unitaire);
			sb.append(",Total_final=" + Total_final);
			sb.append(",Remise______Opportunite=" + String.valueOf(Remise______Opportunite));
			sb.append(",Total_remise_HT=" + String.valueOf(Total_remise_HT));
			sb.append(",Extension__Opportunite=" + Extension__Opportunite);
			sb.append(",Budget_a_valider__Opportunite=" + Budget_a_valider__Opportunite);
			sb.append(",Budget_a_valider_Extension__Opportunite=" + Budget_a_valider_Extension__Opportunite);
			sb.append(",Duree_d_engagement__Opportunite=" + Duree_d_engagement__Opportunite);
			sb.append(",Type_d_engagement__Opportunite=" + Type_d_engagement__Opportunite);
			sb.append(",Demandeur__Opportunite=" + Demandeur__Opportunite);
			sb.append(",Equipe=" + Equipe);
			sb.append(",Cree_le__Opportunite=" + Cree_le__Opportunite);
			sb.append(",Mois_de_creation=" + String.valueOf(Mois_de_creation));
			sb.append(",Annee_de_creation=" + String.valueOf(Annee_de_creation));
			sb.append(",Mois_et_annee_de_creation=" + Mois_et_annee_de_creation);
			sb.append(",Raison_de_statut_OSM__Opportunite=" + Raison_de_statut_OSM__Opportunite);
			sb.append(",Statut_OSM__Opportunite=" + Statut_OSM__Opportunite);
			sb.append(",Statut_activation__Commande=" + Statut_activation__Commande);
			sb.append(",Date_d_activation__Commande=" + Date_d_activation__Commande);
			sb.append(",Mois_d_activation=" + String.valueOf(Mois_d_activation));
			sb.append(",Staut_Final=" + Staut_Final);
			sb.append(",Gouvernorat=" + Gouvernorat);
			sb.append(",Phase_de_l_opportunite__Opportunite=" + Phase_de_l_opportunite__Opportunite);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row58Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class after_tFileInputExcel_35Struct
			implements routines.system.IPersistableRow<after_tFileInputExcel_35Struct> {
		final static byte[] commonByteArrayLock_ORANGE_tzydfvq = new byte[0];
		static byte[] commonByteArray_ORANGE_tzydfvq = new byte[0];

		public String Reference_OSM_mere__Opportunite;

		public String getReference_OSM_mere__Opportunite() {
			return this.Reference_OSM_mere__Opportunite;
		}

		public String Reference_OSM__Opportunite;

		public String getReference_OSM__Opportunite() {
			return this.Reference_OSM__Opportunite;
		}

		public String Client_Prospect__Opportunite;

		public String getClient_Prospect__Opportunite() {
			return this.Client_Prospect__Opportunite;
		}

		public String Offre;

		public String getOffre() {
			return this.Offre;
		}

		public String Nature_Offre;

		public String getNature_Offre() {
			return this.Nature_Offre;
		}

		public String Produit_existant;

		public String getProduit_existant() {
			return this.Produit_existant;
		}

		public String Nature_produit__Produit_existant;

		public String getNature_produit__Produit_existant() {
			return this.Nature_produit__Produit_existant;
		}

		public Integer Quantite;

		public Integer getQuantite() {
			return this.Quantite;
		}

		public String Prix_unitaire;

		public String getPrix_unitaire() {
			return this.Prix_unitaire;
		}

		public String Total_final;

		public String getTotal_final() {
			return this.Total_final;
		}

		public Float Remise______Opportunite;

		public Float getRemise______Opportunite() {
			return this.Remise______Opportunite;
		}

		public Float Total_remise_HT;

		public Float getTotal_remise_HT() {
			return this.Total_remise_HT;
		}

		public String Extension__Opportunite;

		public String getExtension__Opportunite() {
			return this.Extension__Opportunite;
		}

		public String Budget_a_valider__Opportunite;

		public String getBudget_a_valider__Opportunite() {
			return this.Budget_a_valider__Opportunite;
		}

		public String Budget_a_valider_Extension__Opportunite;

		public String getBudget_a_valider_Extension__Opportunite() {
			return this.Budget_a_valider_Extension__Opportunite;
		}

		public String Duree_d_engagement__Opportunite;

		public String getDuree_d_engagement__Opportunite() {
			return this.Duree_d_engagement__Opportunite;
		}

		public String Type_d_engagement__Opportunite;

		public String getType_d_engagement__Opportunite() {
			return this.Type_d_engagement__Opportunite;
		}

		public String Demandeur__Opportunite;

		public String getDemandeur__Opportunite() {
			return this.Demandeur__Opportunite;
		}

		public String Equipe;

		public String getEquipe() {
			return this.Equipe;
		}

		public String Cree_le__Opportunite;

		public String getCree_le__Opportunite() {
			return this.Cree_le__Opportunite;
		}

		public Integer Mois_de_creation;

		public Integer getMois_de_creation() {
			return this.Mois_de_creation;
		}

		public Integer Annee_de_creation;

		public Integer getAnnee_de_creation() {
			return this.Annee_de_creation;
		}

		public String Mois_et_annee_de_creation;

		public String getMois_et_annee_de_creation() {
			return this.Mois_et_annee_de_creation;
		}

		public String Raison_de_statut_OSM__Opportunite;

		public String getRaison_de_statut_OSM__Opportunite() {
			return this.Raison_de_statut_OSM__Opportunite;
		}

		public String Statut_OSM__Opportunite;

		public String getStatut_OSM__Opportunite() {
			return this.Statut_OSM__Opportunite;
		}

		public String Statut_activation__Commande;

		public String getStatut_activation__Commande() {
			return this.Statut_activation__Commande;
		}

		public String Date_d_activation__Commande;

		public String getDate_d_activation__Commande() {
			return this.Date_d_activation__Commande;
		}

		public Integer Mois_d_activation;

		public Integer getMois_d_activation() {
			return this.Mois_d_activation;
		}

		public String Staut_Final;

		public String getStaut_Final() {
			return this.Staut_Final;
		}

		public String Gouvernorat;

		public String getGouvernorat() {
			return this.Gouvernorat;
		}

		public String Phase_de_l_opportunite__Opportunite;

		public String getPhase_de_l_opportunite__Opportunite() {
			return this.Phase_de_l_opportunite__Opportunite;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_tzydfvq.length) {
					if (length < 1024 && commonByteArray_ORANGE_tzydfvq.length == 0) {
						commonByteArray_ORANGE_tzydfvq = new byte[1024];
					} else {
						commonByteArray_ORANGE_tzydfvq = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_tzydfvq, 0, length);
				strReturn = new String(commonByteArray_ORANGE_tzydfvq, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_tzydfvq.length) {
					if (length < 1024 && commonByteArray_ORANGE_tzydfvq.length == 0) {
						commonByteArray_ORANGE_tzydfvq = new byte[1024];
					} else {
						commonByteArray_ORANGE_tzydfvq = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_tzydfvq, 0, length);
				strReturn = new String(commonByteArray_ORANGE_tzydfvq, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ORANGE_tzydfvq) {

				try {

					int length = 0;

					this.Reference_OSM_mere__Opportunite = readString(dis);

					this.Reference_OSM__Opportunite = readString(dis);

					this.Client_Prospect__Opportunite = readString(dis);

					this.Offre = readString(dis);

					this.Nature_Offre = readString(dis);

					this.Produit_existant = readString(dis);

					this.Nature_produit__Produit_existant = readString(dis);

					this.Quantite = readInteger(dis);

					this.Prix_unitaire = readString(dis);

					this.Total_final = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Remise______Opportunite = null;
					} else {
						this.Remise______Opportunite = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Total_remise_HT = null;
					} else {
						this.Total_remise_HT = dis.readFloat();
					}

					this.Extension__Opportunite = readString(dis);

					this.Budget_a_valider__Opportunite = readString(dis);

					this.Budget_a_valider_Extension__Opportunite = readString(dis);

					this.Duree_d_engagement__Opportunite = readString(dis);

					this.Type_d_engagement__Opportunite = readString(dis);

					this.Demandeur__Opportunite = readString(dis);

					this.Equipe = readString(dis);

					this.Cree_le__Opportunite = readString(dis);

					this.Mois_de_creation = readInteger(dis);

					this.Annee_de_creation = readInteger(dis);

					this.Mois_et_annee_de_creation = readString(dis);

					this.Raison_de_statut_OSM__Opportunite = readString(dis);

					this.Statut_OSM__Opportunite = readString(dis);

					this.Statut_activation__Commande = readString(dis);

					this.Date_d_activation__Commande = readString(dis);

					this.Mois_d_activation = readInteger(dis);

					this.Staut_Final = readString(dis);

					this.Gouvernorat = readString(dis);

					this.Phase_de_l_opportunite__Opportunite = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_tzydfvq) {

				try {

					int length = 0;

					this.Reference_OSM_mere__Opportunite = readString(dis);

					this.Reference_OSM__Opportunite = readString(dis);

					this.Client_Prospect__Opportunite = readString(dis);

					this.Offre = readString(dis);

					this.Nature_Offre = readString(dis);

					this.Produit_existant = readString(dis);

					this.Nature_produit__Produit_existant = readString(dis);

					this.Quantite = readInteger(dis);

					this.Prix_unitaire = readString(dis);

					this.Total_final = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Remise______Opportunite = null;
					} else {
						this.Remise______Opportunite = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Total_remise_HT = null;
					} else {
						this.Total_remise_HT = dis.readFloat();
					}

					this.Extension__Opportunite = readString(dis);

					this.Budget_a_valider__Opportunite = readString(dis);

					this.Budget_a_valider_Extension__Opportunite = readString(dis);

					this.Duree_d_engagement__Opportunite = readString(dis);

					this.Type_d_engagement__Opportunite = readString(dis);

					this.Demandeur__Opportunite = readString(dis);

					this.Equipe = readString(dis);

					this.Cree_le__Opportunite = readString(dis);

					this.Mois_de_creation = readInteger(dis);

					this.Annee_de_creation = readInteger(dis);

					this.Mois_et_annee_de_creation = readString(dis);

					this.Raison_de_statut_OSM__Opportunite = readString(dis);

					this.Statut_OSM__Opportunite = readString(dis);

					this.Statut_activation__Commande = readString(dis);

					this.Date_d_activation__Commande = readString(dis);

					this.Mois_d_activation = readInteger(dis);

					this.Staut_Final = readString(dis);

					this.Gouvernorat = readString(dis);

					this.Phase_de_l_opportunite__Opportunite = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Reference_OSM_mere__Opportunite, dos);

				// String

				writeString(this.Reference_OSM__Opportunite, dos);

				// String

				writeString(this.Client_Prospect__Opportunite, dos);

				// String

				writeString(this.Offre, dos);

				// String

				writeString(this.Nature_Offre, dos);

				// String

				writeString(this.Produit_existant, dos);

				// String

				writeString(this.Nature_produit__Produit_existant, dos);

				// Integer

				writeInteger(this.Quantite, dos);

				// String

				writeString(this.Prix_unitaire, dos);

				// String

				writeString(this.Total_final, dos);

				// Float

				if (this.Remise______Opportunite == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Remise______Opportunite);
				}

				// Float

				if (this.Total_remise_HT == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Total_remise_HT);
				}

				// String

				writeString(this.Extension__Opportunite, dos);

				// String

				writeString(this.Budget_a_valider__Opportunite, dos);

				// String

				writeString(this.Budget_a_valider_Extension__Opportunite, dos);

				// String

				writeString(this.Duree_d_engagement__Opportunite, dos);

				// String

				writeString(this.Type_d_engagement__Opportunite, dos);

				// String

				writeString(this.Demandeur__Opportunite, dos);

				// String

				writeString(this.Equipe, dos);

				// String

				writeString(this.Cree_le__Opportunite, dos);

				// Integer

				writeInteger(this.Mois_de_creation, dos);

				// Integer

				writeInteger(this.Annee_de_creation, dos);

				// String

				writeString(this.Mois_et_annee_de_creation, dos);

				// String

				writeString(this.Raison_de_statut_OSM__Opportunite, dos);

				// String

				writeString(this.Statut_OSM__Opportunite, dos);

				// String

				writeString(this.Statut_activation__Commande, dos);

				// String

				writeString(this.Date_d_activation__Commande, dos);

				// Integer

				writeInteger(this.Mois_d_activation, dos);

				// String

				writeString(this.Staut_Final, dos);

				// String

				writeString(this.Gouvernorat, dos);

				// String

				writeString(this.Phase_de_l_opportunite__Opportunite, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Reference_OSM_mere__Opportunite, dos);

				// String

				writeString(this.Reference_OSM__Opportunite, dos);

				// String

				writeString(this.Client_Prospect__Opportunite, dos);

				// String

				writeString(this.Offre, dos);

				// String

				writeString(this.Nature_Offre, dos);

				// String

				writeString(this.Produit_existant, dos);

				// String

				writeString(this.Nature_produit__Produit_existant, dos);

				// Integer

				writeInteger(this.Quantite, dos);

				// String

				writeString(this.Prix_unitaire, dos);

				// String

				writeString(this.Total_final, dos);

				// Float

				if (this.Remise______Opportunite == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Remise______Opportunite);
				}

				// Float

				if (this.Total_remise_HT == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Total_remise_HT);
				}

				// String

				writeString(this.Extension__Opportunite, dos);

				// String

				writeString(this.Budget_a_valider__Opportunite, dos);

				// String

				writeString(this.Budget_a_valider_Extension__Opportunite, dos);

				// String

				writeString(this.Duree_d_engagement__Opportunite, dos);

				// String

				writeString(this.Type_d_engagement__Opportunite, dos);

				// String

				writeString(this.Demandeur__Opportunite, dos);

				// String

				writeString(this.Equipe, dos);

				// String

				writeString(this.Cree_le__Opportunite, dos);

				// Integer

				writeInteger(this.Mois_de_creation, dos);

				// Integer

				writeInteger(this.Annee_de_creation, dos);

				// String

				writeString(this.Mois_et_annee_de_creation, dos);

				// String

				writeString(this.Raison_de_statut_OSM__Opportunite, dos);

				// String

				writeString(this.Statut_OSM__Opportunite, dos);

				// String

				writeString(this.Statut_activation__Commande, dos);

				// String

				writeString(this.Date_d_activation__Commande, dos);

				// Integer

				writeInteger(this.Mois_d_activation, dos);

				// String

				writeString(this.Staut_Final, dos);

				// String

				writeString(this.Gouvernorat, dos);

				// String

				writeString(this.Phase_de_l_opportunite__Opportunite, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Reference_OSM_mere__Opportunite=" + Reference_OSM_mere__Opportunite);
			sb.append(",Reference_OSM__Opportunite=" + Reference_OSM__Opportunite);
			sb.append(",Client_Prospect__Opportunite=" + Client_Prospect__Opportunite);
			sb.append(",Offre=" + Offre);
			sb.append(",Nature_Offre=" + Nature_Offre);
			sb.append(",Produit_existant=" + Produit_existant);
			sb.append(",Nature_produit__Produit_existant=" + Nature_produit__Produit_existant);
			sb.append(",Quantite=" + String.valueOf(Quantite));
			sb.append(",Prix_unitaire=" + Prix_unitaire);
			sb.append(",Total_final=" + Total_final);
			sb.append(",Remise______Opportunite=" + String.valueOf(Remise______Opportunite));
			sb.append(",Total_remise_HT=" + String.valueOf(Total_remise_HT));
			sb.append(",Extension__Opportunite=" + Extension__Opportunite);
			sb.append(",Budget_a_valider__Opportunite=" + Budget_a_valider__Opportunite);
			sb.append(",Budget_a_valider_Extension__Opportunite=" + Budget_a_valider_Extension__Opportunite);
			sb.append(",Duree_d_engagement__Opportunite=" + Duree_d_engagement__Opportunite);
			sb.append(",Type_d_engagement__Opportunite=" + Type_d_engagement__Opportunite);
			sb.append(",Demandeur__Opportunite=" + Demandeur__Opportunite);
			sb.append(",Equipe=" + Equipe);
			sb.append(",Cree_le__Opportunite=" + Cree_le__Opportunite);
			sb.append(",Mois_de_creation=" + String.valueOf(Mois_de_creation));
			sb.append(",Annee_de_creation=" + String.valueOf(Annee_de_creation));
			sb.append(",Mois_et_annee_de_creation=" + Mois_et_annee_de_creation);
			sb.append(",Raison_de_statut_OSM__Opportunite=" + Raison_de_statut_OSM__Opportunite);
			sb.append(",Statut_OSM__Opportunite=" + Statut_OSM__Opportunite);
			sb.append(",Statut_activation__Commande=" + Statut_activation__Commande);
			sb.append(",Date_d_activation__Commande=" + Date_d_activation__Commande);
			sb.append(",Mois_d_activation=" + String.valueOf(Mois_d_activation));
			sb.append(",Staut_Final=" + Staut_Final);
			sb.append(",Gouvernorat=" + Gouvernorat);
			sb.append(",Phase_de_l_opportunite__Opportunite=" + Phase_de_l_opportunite__Opportunite);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(after_tFileInputExcel_35Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tFileInputExcel_35Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputExcel_35_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				tDBInput_14Process(globalMap);
				tDBInput_15Process(globalMap);
				tDBInput_16Process(globalMap);
				tDBInput_17Process(globalMap);
				tDBInput_18Process(globalMap);

				row58Struct row58 = new row58Struct();
				Dim_Reference_OSM_Opportunite_mobileStruct Dim_Reference_OSM_Opportunite_mobile = new Dim_Reference_OSM_Opportunite_mobileStruct();
				bStruct b = new bStruct();
				row68Struct row68 = new row68Struct();
				Dim_type_engagement_mobileStruct Dim_type_engagement_mobile = new Dim_type_engagement_mobileStruct();
				nStruct n = new nStruct();
				row70Struct row70 = new row70Struct();
				Extension_idStruct Extension_id = new Extension_idStruct();
				qStruct q = new qStruct();
				row71Struct row71 = new row71Struct();
				Dim_nature_offre_mobileStruct Dim_nature_offre_mobile = new Dim_nature_offre_mobileStruct();
				vStruct v = new vStruct();
				row66Struct row66 = new row66Struct();
				Dim_offre_mobileStruct Dim_offre_mobile = new Dim_offre_mobileStruct();
				cStruct c = new cStruct();
				row64Struct row64 = new row64Struct();

				/**
				 * [tDBOutput_16 begin ] start
				 */

				ok_Hash.put("tDBOutput_16", false);
				start_Hash.put("tDBOutput_16", System.currentTimeMillis());

				currentComponent = "tDBOutput_16";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row68");
				}

				int tos_count_tDBOutput_16 = 0;

				int nb_line_tDBOutput_16 = 0;
				int nb_line_update_tDBOutput_16 = 0;
				int nb_line_inserted_tDBOutput_16 = 0;
				int nb_line_deleted_tDBOutput_16 = 0;
				int nb_line_rejected_tDBOutput_16 = 0;

				int deletedCount_tDBOutput_16 = 0;
				int updatedCount_tDBOutput_16 = 0;
				int insertedCount_tDBOutput_16 = 0;
				int rowsToCommitCount_tDBOutput_16 = 0;
				int rejectedCount_tDBOutput_16 = 0;
				String dbschema_tDBOutput_16 = null;
				String tableName_tDBOutput_16 = null;
				boolean whetherReject_tDBOutput_16 = false;

				java.util.Calendar calendar_tDBOutput_16 = java.util.Calendar.getInstance();
				long year1_tDBOutput_16 = TalendDate.parseDate("yyyy-MM-dd", "0001-01-01").getTime();
				long year2_tDBOutput_16 = TalendDate.parseDate("yyyy-MM-dd", "1753-01-01").getTime();
				long year10000_tDBOutput_16 = TalendDate.parseDate("yyyy-MM-dd HH:mm:ss", "9999-12-31 24:00:00")
						.getTime();
				long date_tDBOutput_16;

				java.util.Calendar calendar_datetimeoffset_tDBOutput_16 = java.util.Calendar
						.getInstance(java.util.TimeZone.getTimeZone("UTC"));

				java.sql.Connection conn_tDBOutput_16 = null;
				String dbUser_tDBOutput_16 = null;
				dbschema_tDBOutput_16 = "dbo";
				String driverClass_tDBOutput_16 = "net.sourceforge.jtds.jdbc.Driver";

				java.lang.Class.forName(driverClass_tDBOutput_16);
				String port_tDBOutput_16 = "1433";
				String dbname_tDBOutput_16 = "orange_DW__";
				String url_tDBOutput_16 = "jdbc:jtds:sqlserver://" + "DESKTOP-6RL9E91";
				if (!"".equals(port_tDBOutput_16)) {
					url_tDBOutput_16 += ":" + "1433";
				}
				if (!"".equals(dbname_tDBOutput_16)) {
					url_tDBOutput_16 += "//" + "orange_DW__";

				}
				url_tDBOutput_16 += ";appName=" + projectName + ";" + "";
				dbUser_tDBOutput_16 = "sa";

				final String decryptedPassword_tDBOutput_16 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:ULhkMDiPN21q2GmaOqm3Fae1JcaZug3MSmuzCg1cbqeETlIH8Gk=");

				String dbPwd_tDBOutput_16 = decryptedPassword_tDBOutput_16;
				conn_tDBOutput_16 = java.sql.DriverManager.getConnection(url_tDBOutput_16, dbUser_tDBOutput_16,
						dbPwd_tDBOutput_16);

				resourceMap.put("conn_tDBOutput_16", conn_tDBOutput_16);

				conn_tDBOutput_16.setAutoCommit(false);
				int commitEvery_tDBOutput_16 = 10000;
				int commitCounter_tDBOutput_16 = 0;

				int batchSize_tDBOutput_16 = 10000;
				int batchSizeCounter_tDBOutput_16 = 0;

				if (dbschema_tDBOutput_16 == null || dbschema_tDBOutput_16.trim().length() == 0) {
					tableName_tDBOutput_16 = "Dim_Reference_OSM";
				} else {
					tableName_tDBOutput_16 = dbschema_tDBOutput_16 + "].[" + "Dim_Reference_OSM";
				}
				int count_tDBOutput_16 = 0;

				String insert_tDBOutput_16 = "INSERT INTO [" + tableName_tDBOutput_16
						+ "] ([Reference_OSM__Opportunite]) VALUES (?)";
				java.sql.PreparedStatement pstmt_tDBOutput_16 = conn_tDBOutput_16.prepareStatement(insert_tDBOutput_16);
				resourceMap.put("pstmt_tDBOutput_16", pstmt_tDBOutput_16);

				/**
				 * [tDBOutput_16 begin ] stop
				 */

				/**
				 * [tUniqRow_15 begin ] start
				 */

				ok_Hash.put("tUniqRow_15", false);
				start_Hash.put("tUniqRow_15", System.currentTimeMillis());

				currentComponent = "tUniqRow_15";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "b");
				}

				int tos_count_tUniqRow_15 = 0;

				class KeyStruct_tUniqRow_15 {

					private static final int DEFAULT_HASHCODE = 1;
					private static final int PRIME = 31;
					private int hashCode = DEFAULT_HASHCODE;
					public boolean hashCodeDirty = true;

					String Reference_OSM__Opportunite;

					@Override
					public int hashCode() {
						if (this.hashCodeDirty) {
							final int prime = PRIME;
							int result = DEFAULT_HASHCODE;

							result = prime * result + ((this.Reference_OSM__Opportunite == null) ? 0
									: this.Reference_OSM__Opportunite.hashCode());

							this.hashCode = result;
							this.hashCodeDirty = false;
						}
						return this.hashCode;
					}

					@Override
					public boolean equals(Object obj) {
						if (this == obj)
							return true;
						if (obj == null)
							return false;
						if (getClass() != obj.getClass())
							return false;
						final KeyStruct_tUniqRow_15 other = (KeyStruct_tUniqRow_15) obj;

						if (this.Reference_OSM__Opportunite == null) {
							if (other.Reference_OSM__Opportunite != null)
								return false;

						} else if (!this.Reference_OSM__Opportunite.equals(other.Reference_OSM__Opportunite))

							return false;

						return true;
					}

				}

				int nb_uniques_tUniqRow_15 = 0;
				int nb_duplicates_tUniqRow_15 = 0;
				KeyStruct_tUniqRow_15 finder_tUniqRow_15 = new KeyStruct_tUniqRow_15();
				java.util.Set<KeyStruct_tUniqRow_15> keystUniqRow_15 = new java.util.HashSet<KeyStruct_tUniqRow_15>();

				/**
				 * [tUniqRow_15 begin ] stop
				 */

				/**
				 * [tMap_51 begin ] start
				 */

				ok_Hash.put("tMap_51", false);
				start_Hash.put("tMap_51", System.currentTimeMillis());

				currentComponent = "tMap_51";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0,
							"Dim_Reference_OSM_Opportunite_mobile");
				}

				int tos_count_tMap_51 = 0;

// ###############################
// # Lookup's keys initialization

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row67Struct> tHash_Lookup_row67 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row67Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row67Struct>) globalMap
						.get("tHash_Lookup_row67"));

				row67Struct row67HashKey = new row67Struct();
				row67Struct row67Default = new row67Struct();
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_51__Struct {
				}
				Var__tMap_51__Struct Var__tMap_51 = new Var__tMap_51__Struct();
// ###############################

// ###############################
// # Outputs initialization
				bStruct b_tmp = new bStruct();
// ###############################

				/**
				 * [tMap_51 begin ] stop
				 */

				/**
				 * [tDBOutput_17 begin ] start
				 */

				ok_Hash.put("tDBOutput_17", false);
				start_Hash.put("tDBOutput_17", System.currentTimeMillis());

				currentComponent = "tDBOutput_17";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row70");
				}

				int tos_count_tDBOutput_17 = 0;

				int nb_line_tDBOutput_17 = 0;
				int nb_line_update_tDBOutput_17 = 0;
				int nb_line_inserted_tDBOutput_17 = 0;
				int nb_line_deleted_tDBOutput_17 = 0;
				int nb_line_rejected_tDBOutput_17 = 0;

				int deletedCount_tDBOutput_17 = 0;
				int updatedCount_tDBOutput_17 = 0;
				int insertedCount_tDBOutput_17 = 0;
				int rowsToCommitCount_tDBOutput_17 = 0;
				int rejectedCount_tDBOutput_17 = 0;
				String dbschema_tDBOutput_17 = null;
				String tableName_tDBOutput_17 = null;
				boolean whetherReject_tDBOutput_17 = false;

				java.util.Calendar calendar_tDBOutput_17 = java.util.Calendar.getInstance();
				long year1_tDBOutput_17 = TalendDate.parseDate("yyyy-MM-dd", "0001-01-01").getTime();
				long year2_tDBOutput_17 = TalendDate.parseDate("yyyy-MM-dd", "1753-01-01").getTime();
				long year10000_tDBOutput_17 = TalendDate.parseDate("yyyy-MM-dd HH:mm:ss", "9999-12-31 24:00:00")
						.getTime();
				long date_tDBOutput_17;

				java.util.Calendar calendar_datetimeoffset_tDBOutput_17 = java.util.Calendar
						.getInstance(java.util.TimeZone.getTimeZone("UTC"));

				java.sql.Connection conn_tDBOutput_17 = null;
				String dbUser_tDBOutput_17 = null;
				dbschema_tDBOutput_17 = "dbo";
				String driverClass_tDBOutput_17 = "net.sourceforge.jtds.jdbc.Driver";

				java.lang.Class.forName(driverClass_tDBOutput_17);
				String port_tDBOutput_17 = "1433";
				String dbname_tDBOutput_17 = "orange_DW__";
				String url_tDBOutput_17 = "jdbc:jtds:sqlserver://" + "DESKTOP-6RL9E91";
				if (!"".equals(port_tDBOutput_17)) {
					url_tDBOutput_17 += ":" + "1433";
				}
				if (!"".equals(dbname_tDBOutput_17)) {
					url_tDBOutput_17 += "//" + "orange_DW__";

				}
				url_tDBOutput_17 += ";appName=" + projectName + ";" + "";
				dbUser_tDBOutput_17 = "sa";

				final String decryptedPassword_tDBOutput_17 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:zQitEIC3rQV8DYIRAMMwa4obNee8QpLjJD4Rp1Nqh+1qW5e+Vp8=");

				String dbPwd_tDBOutput_17 = decryptedPassword_tDBOutput_17;
				conn_tDBOutput_17 = java.sql.DriverManager.getConnection(url_tDBOutput_17, dbUser_tDBOutput_17,
						dbPwd_tDBOutput_17);

				resourceMap.put("conn_tDBOutput_17", conn_tDBOutput_17);

				conn_tDBOutput_17.setAutoCommit(false);
				int commitEvery_tDBOutput_17 = 10000;
				int commitCounter_tDBOutput_17 = 0;

				int batchSize_tDBOutput_17 = 10000;
				int batchSizeCounter_tDBOutput_17 = 0;

				if (dbschema_tDBOutput_17 == null || dbschema_tDBOutput_17.trim().length() == 0) {
					tableName_tDBOutput_17 = "Dim_type_engagement";
				} else {
					tableName_tDBOutput_17 = dbschema_tDBOutput_17 + "].[" + "Dim_type_engagement";
				}
				int count_tDBOutput_17 = 0;

				String insert_tDBOutput_17 = "INSERT INTO [" + tableName_tDBOutput_17
						+ "] ([Type_d_engagement__Opportunite]) VALUES (?)";
				java.sql.PreparedStatement pstmt_tDBOutput_17 = conn_tDBOutput_17.prepareStatement(insert_tDBOutput_17);
				resourceMap.put("pstmt_tDBOutput_17", pstmt_tDBOutput_17);

				/**
				 * [tDBOutput_17 begin ] stop
				 */

				/**
				 * [tUniqRow_16 begin ] start
				 */

				ok_Hash.put("tUniqRow_16", false);
				start_Hash.put("tUniqRow_16", System.currentTimeMillis());

				currentComponent = "tUniqRow_16";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "n");
				}

				int tos_count_tUniqRow_16 = 0;

				class KeyStruct_tUniqRow_16 {

					private static final int DEFAULT_HASHCODE = 1;
					private static final int PRIME = 31;
					private int hashCode = DEFAULT_HASHCODE;
					public boolean hashCodeDirty = true;

					String Type_d_engagement__Opportunite;

					@Override
					public int hashCode() {
						if (this.hashCodeDirty) {
							final int prime = PRIME;
							int result = DEFAULT_HASHCODE;

							result = prime * result + ((this.Type_d_engagement__Opportunite == null) ? 0
									: this.Type_d_engagement__Opportunite.hashCode());

							this.hashCode = result;
							this.hashCodeDirty = false;
						}
						return this.hashCode;
					}

					@Override
					public boolean equals(Object obj) {
						if (this == obj)
							return true;
						if (obj == null)
							return false;
						if (getClass() != obj.getClass())
							return false;
						final KeyStruct_tUniqRow_16 other = (KeyStruct_tUniqRow_16) obj;

						if (this.Type_d_engagement__Opportunite == null) {
							if (other.Type_d_engagement__Opportunite != null)
								return false;

						} else if (!this.Type_d_engagement__Opportunite.equals(other.Type_d_engagement__Opportunite))

							return false;

						return true;
					}

				}

				int nb_uniques_tUniqRow_16 = 0;
				int nb_duplicates_tUniqRow_16 = 0;
				KeyStruct_tUniqRow_16 finder_tUniqRow_16 = new KeyStruct_tUniqRow_16();
				java.util.Set<KeyStruct_tUniqRow_16> keystUniqRow_16 = new java.util.HashSet<KeyStruct_tUniqRow_16>();

				/**
				 * [tUniqRow_16 begin ] stop
				 */

				/**
				 * [tMap_52 begin ] start
				 */

				ok_Hash.put("tMap_52", false);
				start_Hash.put("tMap_52", System.currentTimeMillis());

				currentComponent = "tMap_52";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "Dim_type_engagement_mobile");
				}

				int tos_count_tMap_52 = 0;

// ###############################
// # Lookup's keys initialization

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row69Struct> tHash_Lookup_row69 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row69Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row69Struct>) globalMap
						.get("tHash_Lookup_row69"));

				row69Struct row69HashKey = new row69Struct();
				row69Struct row69Default = new row69Struct();
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_52__Struct {
				}
				Var__tMap_52__Struct Var__tMap_52 = new Var__tMap_52__Struct();
// ###############################

// ###############################
// # Outputs initialization
				nStruct n_tmp = new nStruct();
// ###############################

				/**
				 * [tMap_52 begin ] stop
				 */

				/**
				 * [tDBOutput_18 begin ] start
				 */

				ok_Hash.put("tDBOutput_18", false);
				start_Hash.put("tDBOutput_18", System.currentTimeMillis());

				currentComponent = "tDBOutput_18";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row71");
				}

				int tos_count_tDBOutput_18 = 0;

				int nb_line_tDBOutput_18 = 0;
				int nb_line_update_tDBOutput_18 = 0;
				int nb_line_inserted_tDBOutput_18 = 0;
				int nb_line_deleted_tDBOutput_18 = 0;
				int nb_line_rejected_tDBOutput_18 = 0;

				int deletedCount_tDBOutput_18 = 0;
				int updatedCount_tDBOutput_18 = 0;
				int insertedCount_tDBOutput_18 = 0;
				int rowsToCommitCount_tDBOutput_18 = 0;
				int rejectedCount_tDBOutput_18 = 0;
				String dbschema_tDBOutput_18 = null;
				String tableName_tDBOutput_18 = null;
				boolean whetherReject_tDBOutput_18 = false;

				java.util.Calendar calendar_tDBOutput_18 = java.util.Calendar.getInstance();
				long year1_tDBOutput_18 = TalendDate.parseDate("yyyy-MM-dd", "0001-01-01").getTime();
				long year2_tDBOutput_18 = TalendDate.parseDate("yyyy-MM-dd", "1753-01-01").getTime();
				long year10000_tDBOutput_18 = TalendDate.parseDate("yyyy-MM-dd HH:mm:ss", "9999-12-31 24:00:00")
						.getTime();
				long date_tDBOutput_18;

				java.util.Calendar calendar_datetimeoffset_tDBOutput_18 = java.util.Calendar
						.getInstance(java.util.TimeZone.getTimeZone("UTC"));

				java.sql.Connection conn_tDBOutput_18 = null;
				String dbUser_tDBOutput_18 = null;
				dbschema_tDBOutput_18 = "dbo";
				String driverClass_tDBOutput_18 = "net.sourceforge.jtds.jdbc.Driver";

				java.lang.Class.forName(driverClass_tDBOutput_18);
				String port_tDBOutput_18 = "1433";
				String dbname_tDBOutput_18 = "orange_DW__";
				String url_tDBOutput_18 = "jdbc:jtds:sqlserver://" + "DESKTOP-6RL9E91";
				if (!"".equals(port_tDBOutput_18)) {
					url_tDBOutput_18 += ":" + "1433";
				}
				if (!"".equals(dbname_tDBOutput_18)) {
					url_tDBOutput_18 += "//" + "orange_DW__";

				}
				url_tDBOutput_18 += ";appName=" + projectName + ";" + "";
				dbUser_tDBOutput_18 = "sa";

				final String decryptedPassword_tDBOutput_18 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:auYi5aSqvOoI2GNktq64iR0zLNJxFCiZPgLmm+EWdjbo2ZMfhtE=");

				String dbPwd_tDBOutput_18 = decryptedPassword_tDBOutput_18;
				conn_tDBOutput_18 = java.sql.DriverManager.getConnection(url_tDBOutput_18, dbUser_tDBOutput_18,
						dbPwd_tDBOutput_18);

				resourceMap.put("conn_tDBOutput_18", conn_tDBOutput_18);

				conn_tDBOutput_18.setAutoCommit(false);
				int commitEvery_tDBOutput_18 = 10000;
				int commitCounter_tDBOutput_18 = 0;

				int batchSize_tDBOutput_18 = 10000;
				int batchSizeCounter_tDBOutput_18 = 0;

				if (dbschema_tDBOutput_18 == null || dbschema_tDBOutput_18.trim().length() == 0) {
					tableName_tDBOutput_18 = "Dim_Extension_mobile";
				} else {
					tableName_tDBOutput_18 = dbschema_tDBOutput_18 + "].[" + "Dim_Extension_mobile";
				}
				int count_tDBOutput_18 = 0;

				String insert_tDBOutput_18 = "INSERT INTO [" + tableName_tDBOutput_18
						+ "] ([Extension__Opportunite]) VALUES (?)";
				java.sql.PreparedStatement pstmt_tDBOutput_18 = conn_tDBOutput_18.prepareStatement(insert_tDBOutput_18);
				resourceMap.put("pstmt_tDBOutput_18", pstmt_tDBOutput_18);

				/**
				 * [tDBOutput_18 begin ] stop
				 */

				/**
				 * [tUniqRow_17 begin ] start
				 */

				ok_Hash.put("tUniqRow_17", false);
				start_Hash.put("tUniqRow_17", System.currentTimeMillis());

				currentComponent = "tUniqRow_17";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "q");
				}

				int tos_count_tUniqRow_17 = 0;

				class KeyStruct_tUniqRow_17 {

					private static final int DEFAULT_HASHCODE = 1;
					private static final int PRIME = 31;
					private int hashCode = DEFAULT_HASHCODE;
					public boolean hashCodeDirty = true;

					String Extension__Opportunite;

					@Override
					public int hashCode() {
						if (this.hashCodeDirty) {
							final int prime = PRIME;
							int result = DEFAULT_HASHCODE;

							result = prime * result + ((this.Extension__Opportunite == null) ? 0
									: this.Extension__Opportunite.hashCode());

							this.hashCode = result;
							this.hashCodeDirty = false;
						}
						return this.hashCode;
					}

					@Override
					public boolean equals(Object obj) {
						if (this == obj)
							return true;
						if (obj == null)
							return false;
						if (getClass() != obj.getClass())
							return false;
						final KeyStruct_tUniqRow_17 other = (KeyStruct_tUniqRow_17) obj;

						if (this.Extension__Opportunite == null) {
							if (other.Extension__Opportunite != null)
								return false;

						} else if (!this.Extension__Opportunite.equals(other.Extension__Opportunite))

							return false;

						return true;
					}

				}

				int nb_uniques_tUniqRow_17 = 0;
				int nb_duplicates_tUniqRow_17 = 0;
				KeyStruct_tUniqRow_17 finder_tUniqRow_17 = new KeyStruct_tUniqRow_17();
				java.util.Set<KeyStruct_tUniqRow_17> keystUniqRow_17 = new java.util.HashSet<KeyStruct_tUniqRow_17>();

				/**
				 * [tUniqRow_17 begin ] stop
				 */

				/**
				 * [tMap_53 begin ] start
				 */

				ok_Hash.put("tMap_53", false);
				start_Hash.put("tMap_53", System.currentTimeMillis());

				currentComponent = "tMap_53";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "Extension_id");
				}

				int tos_count_tMap_53 = 0;

// ###############################
// # Lookup's keys initialization

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row72Struct> tHash_Lookup_row72 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row72Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row72Struct>) globalMap
						.get("tHash_Lookup_row72"));

				row72Struct row72HashKey = new row72Struct();
				row72Struct row72Default = new row72Struct();
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_53__Struct {
				}
				Var__tMap_53__Struct Var__tMap_53 = new Var__tMap_53__Struct();
// ###############################

// ###############################
// # Outputs initialization
				qStruct q_tmp = new qStruct();
// ###############################

				/**
				 * [tMap_53 begin ] stop
				 */

				/**
				 * [tDBOutput_15 begin ] start
				 */

				ok_Hash.put("tDBOutput_15", false);
				start_Hash.put("tDBOutput_15", System.currentTimeMillis());

				currentComponent = "tDBOutput_15";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row66");
				}

				int tos_count_tDBOutput_15 = 0;

				int nb_line_tDBOutput_15 = 0;
				int nb_line_update_tDBOutput_15 = 0;
				int nb_line_inserted_tDBOutput_15 = 0;
				int nb_line_deleted_tDBOutput_15 = 0;
				int nb_line_rejected_tDBOutput_15 = 0;

				int deletedCount_tDBOutput_15 = 0;
				int updatedCount_tDBOutput_15 = 0;
				int insertedCount_tDBOutput_15 = 0;
				int rowsToCommitCount_tDBOutput_15 = 0;
				int rejectedCount_tDBOutput_15 = 0;
				String dbschema_tDBOutput_15 = null;
				String tableName_tDBOutput_15 = null;
				boolean whetherReject_tDBOutput_15 = false;

				java.util.Calendar calendar_tDBOutput_15 = java.util.Calendar.getInstance();
				long year1_tDBOutput_15 = TalendDate.parseDate("yyyy-MM-dd", "0001-01-01").getTime();
				long year2_tDBOutput_15 = TalendDate.parseDate("yyyy-MM-dd", "1753-01-01").getTime();
				long year10000_tDBOutput_15 = TalendDate.parseDate("yyyy-MM-dd HH:mm:ss", "9999-12-31 24:00:00")
						.getTime();
				long date_tDBOutput_15;

				java.util.Calendar calendar_datetimeoffset_tDBOutput_15 = java.util.Calendar
						.getInstance(java.util.TimeZone.getTimeZone("UTC"));

				java.sql.Connection conn_tDBOutput_15 = null;
				String dbUser_tDBOutput_15 = null;
				dbschema_tDBOutput_15 = "dbo";
				String driverClass_tDBOutput_15 = "net.sourceforge.jtds.jdbc.Driver";

				java.lang.Class.forName(driverClass_tDBOutput_15);
				String port_tDBOutput_15 = "1433";
				String dbname_tDBOutput_15 = "orange_DW__";
				String url_tDBOutput_15 = "jdbc:jtds:sqlserver://" + "DESKTOP-6RL9E91";
				if (!"".equals(port_tDBOutput_15)) {
					url_tDBOutput_15 += ":" + "1433";
				}
				if (!"".equals(dbname_tDBOutput_15)) {
					url_tDBOutput_15 += "//" + "orange_DW__";

				}
				url_tDBOutput_15 += ";appName=" + projectName + ";" + "";
				dbUser_tDBOutput_15 = "sa";

				final String decryptedPassword_tDBOutput_15 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:fhkjiyp8nYjFyerCwJcbOS4g2WtvZnzg7k9v8nDcTVLSCzifBrE=");

				String dbPwd_tDBOutput_15 = decryptedPassword_tDBOutput_15;
				conn_tDBOutput_15 = java.sql.DriverManager.getConnection(url_tDBOutput_15, dbUser_tDBOutput_15,
						dbPwd_tDBOutput_15);

				resourceMap.put("conn_tDBOutput_15", conn_tDBOutput_15);

				conn_tDBOutput_15.setAutoCommit(false);
				int commitEvery_tDBOutput_15 = 10000;
				int commitCounter_tDBOutput_15 = 0;

				int batchSize_tDBOutput_15 = 10000;
				int batchSizeCounter_tDBOutput_15 = 0;

				if (dbschema_tDBOutput_15 == null || dbschema_tDBOutput_15.trim().length() == 0) {
					tableName_tDBOutput_15 = "Dim_nature_offre";
				} else {
					tableName_tDBOutput_15 = dbschema_tDBOutput_15 + "].[" + "Dim_nature_offre";
				}
				int count_tDBOutput_15 = 0;

				String insert_tDBOutput_15 = "INSERT INTO [" + tableName_tDBOutput_15 + "] ([Nature_Offre]) VALUES (?)";
				java.sql.PreparedStatement pstmt_tDBOutput_15 = conn_tDBOutput_15.prepareStatement(insert_tDBOutput_15);
				resourceMap.put("pstmt_tDBOutput_15", pstmt_tDBOutput_15);

				/**
				 * [tDBOutput_15 begin ] stop
				 */

				/**
				 * [tUniqRow_14 begin ] start
				 */

				ok_Hash.put("tUniqRow_14", false);
				start_Hash.put("tUniqRow_14", System.currentTimeMillis());

				currentComponent = "tUniqRow_14";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "v");
				}

				int tos_count_tUniqRow_14 = 0;

				class KeyStruct_tUniqRow_14 {

					private static final int DEFAULT_HASHCODE = 1;
					private static final int PRIME = 31;
					private int hashCode = DEFAULT_HASHCODE;
					public boolean hashCodeDirty = true;

					String Nature_Offre;

					@Override
					public int hashCode() {
						if (this.hashCodeDirty) {
							final int prime = PRIME;
							int result = DEFAULT_HASHCODE;

							result = prime * result + ((this.Nature_Offre == null) ? 0 : this.Nature_Offre.hashCode());

							this.hashCode = result;
							this.hashCodeDirty = false;
						}
						return this.hashCode;
					}

					@Override
					public boolean equals(Object obj) {
						if (this == obj)
							return true;
						if (obj == null)
							return false;
						if (getClass() != obj.getClass())
							return false;
						final KeyStruct_tUniqRow_14 other = (KeyStruct_tUniqRow_14) obj;

						if (this.Nature_Offre == null) {
							if (other.Nature_Offre != null)
								return false;

						} else if (!this.Nature_Offre.equals(other.Nature_Offre))

							return false;

						return true;
					}

				}

				int nb_uniques_tUniqRow_14 = 0;
				int nb_duplicates_tUniqRow_14 = 0;
				KeyStruct_tUniqRow_14 finder_tUniqRow_14 = new KeyStruct_tUniqRow_14();
				java.util.Set<KeyStruct_tUniqRow_14> keystUniqRow_14 = new java.util.HashSet<KeyStruct_tUniqRow_14>();

				/**
				 * [tUniqRow_14 begin ] stop
				 */

				/**
				 * [tMap_50 begin ] start
				 */

				ok_Hash.put("tMap_50", false);
				start_Hash.put("tMap_50", System.currentTimeMillis());

				currentComponent = "tMap_50";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "Dim_nature_offre_mobile");
				}

				int tos_count_tMap_50 = 0;

// ###############################
// # Lookup's keys initialization

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row65Struct> tHash_Lookup_row65 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row65Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row65Struct>) globalMap
						.get("tHash_Lookup_row65"));

				row65Struct row65HashKey = new row65Struct();
				row65Struct row65Default = new row65Struct();
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_50__Struct {
				}
				Var__tMap_50__Struct Var__tMap_50 = new Var__tMap_50__Struct();
// ###############################

// ###############################
// # Outputs initialization
				vStruct v_tmp = new vStruct();
// ###############################

				/**
				 * [tMap_50 begin ] stop
				 */

				/**
				 * [tDBOutput_14 begin ] start
				 */

				ok_Hash.put("tDBOutput_14", false);
				start_Hash.put("tDBOutput_14", System.currentTimeMillis());

				currentComponent = "tDBOutput_14";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row64");
				}

				int tos_count_tDBOutput_14 = 0;

				int nb_line_tDBOutput_14 = 0;
				int nb_line_update_tDBOutput_14 = 0;
				int nb_line_inserted_tDBOutput_14 = 0;
				int nb_line_deleted_tDBOutput_14 = 0;
				int nb_line_rejected_tDBOutput_14 = 0;

				int deletedCount_tDBOutput_14 = 0;
				int updatedCount_tDBOutput_14 = 0;
				int insertedCount_tDBOutput_14 = 0;
				int rowsToCommitCount_tDBOutput_14 = 0;
				int rejectedCount_tDBOutput_14 = 0;
				String dbschema_tDBOutput_14 = null;
				String tableName_tDBOutput_14 = null;
				boolean whetherReject_tDBOutput_14 = false;

				java.util.Calendar calendar_tDBOutput_14 = java.util.Calendar.getInstance();
				long year1_tDBOutput_14 = TalendDate.parseDate("yyyy-MM-dd", "0001-01-01").getTime();
				long year2_tDBOutput_14 = TalendDate.parseDate("yyyy-MM-dd", "1753-01-01").getTime();
				long year10000_tDBOutput_14 = TalendDate.parseDate("yyyy-MM-dd HH:mm:ss", "9999-12-31 24:00:00")
						.getTime();
				long date_tDBOutput_14;

				java.util.Calendar calendar_datetimeoffset_tDBOutput_14 = java.util.Calendar
						.getInstance(java.util.TimeZone.getTimeZone("UTC"));

				java.sql.Connection conn_tDBOutput_14 = null;
				String dbUser_tDBOutput_14 = null;
				dbschema_tDBOutput_14 = "dbo";
				String driverClass_tDBOutput_14 = "net.sourceforge.jtds.jdbc.Driver";

				java.lang.Class.forName(driverClass_tDBOutput_14);
				String port_tDBOutput_14 = "1433";
				String dbname_tDBOutput_14 = "orange_DW__";
				String url_tDBOutput_14 = "jdbc:jtds:sqlserver://" + "DESKTOP-6RL9E91";
				if (!"".equals(port_tDBOutput_14)) {
					url_tDBOutput_14 += ":" + "1433";
				}
				if (!"".equals(dbname_tDBOutput_14)) {
					url_tDBOutput_14 += "//" + "orange_DW__";

				}
				url_tDBOutput_14 += ";appName=" + projectName + ";" + "";
				dbUser_tDBOutput_14 = "sa";

				final String decryptedPassword_tDBOutput_14 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:Q2gs8XRTEVnrq+S6LI2REsdOTy/XqjpJMbfK8LaRsdy99yjDgUA=");

				String dbPwd_tDBOutput_14 = decryptedPassword_tDBOutput_14;
				conn_tDBOutput_14 = java.sql.DriverManager.getConnection(url_tDBOutput_14, dbUser_tDBOutput_14,
						dbPwd_tDBOutput_14);

				resourceMap.put("conn_tDBOutput_14", conn_tDBOutput_14);

				conn_tDBOutput_14.setAutoCommit(false);
				int commitEvery_tDBOutput_14 = 10000;
				int commitCounter_tDBOutput_14 = 0;

				int batchSize_tDBOutput_14 = 10000;
				int batchSizeCounter_tDBOutput_14 = 0;

				if (dbschema_tDBOutput_14 == null || dbschema_tDBOutput_14.trim().length() == 0) {
					tableName_tDBOutput_14 = "Dim_offre";
				} else {
					tableName_tDBOutput_14 = dbschema_tDBOutput_14 + "].[" + "Dim_offre";
				}
				int count_tDBOutput_14 = 0;

				String insert_tDBOutput_14 = "INSERT INTO [" + tableName_tDBOutput_14 + "] ([Offre]) VALUES (?)";
				java.sql.PreparedStatement pstmt_tDBOutput_14 = conn_tDBOutput_14.prepareStatement(insert_tDBOutput_14);
				resourceMap.put("pstmt_tDBOutput_14", pstmt_tDBOutput_14);

				/**
				 * [tDBOutput_14 begin ] stop
				 */

				/**
				 * [tUniqRow_13 begin ] start
				 */

				ok_Hash.put("tUniqRow_13", false);
				start_Hash.put("tUniqRow_13", System.currentTimeMillis());

				currentComponent = "tUniqRow_13";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "c");
				}

				int tos_count_tUniqRow_13 = 0;

				class KeyStruct_tUniqRow_13 {

					private static final int DEFAULT_HASHCODE = 1;
					private static final int PRIME = 31;
					private int hashCode = DEFAULT_HASHCODE;
					public boolean hashCodeDirty = true;

					String Offre;

					@Override
					public int hashCode() {
						if (this.hashCodeDirty) {
							final int prime = PRIME;
							int result = DEFAULT_HASHCODE;

							result = prime * result + ((this.Offre == null) ? 0 : this.Offre.hashCode());

							this.hashCode = result;
							this.hashCodeDirty = false;
						}
						return this.hashCode;
					}

					@Override
					public boolean equals(Object obj) {
						if (this == obj)
							return true;
						if (obj == null)
							return false;
						if (getClass() != obj.getClass())
							return false;
						final KeyStruct_tUniqRow_13 other = (KeyStruct_tUniqRow_13) obj;

						if (this.Offre == null) {
							if (other.Offre != null)
								return false;

						} else if (!this.Offre.equals(other.Offre))

							return false;

						return true;
					}

				}

				int nb_uniques_tUniqRow_13 = 0;
				int nb_duplicates_tUniqRow_13 = 0;
				KeyStruct_tUniqRow_13 finder_tUniqRow_13 = new KeyStruct_tUniqRow_13();
				java.util.Set<KeyStruct_tUniqRow_13> keystUniqRow_13 = new java.util.HashSet<KeyStruct_tUniqRow_13>();

				/**
				 * [tUniqRow_13 begin ] stop
				 */

				/**
				 * [tMap_49 begin ] start
				 */

				ok_Hash.put("tMap_49", false);
				start_Hash.put("tMap_49", System.currentTimeMillis());

				currentComponent = "tMap_49";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "Dim_offre_mobile");
				}

				int tos_count_tMap_49 = 0;

// ###############################
// # Lookup's keys initialization

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row62Struct> tHash_Lookup_row62 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row62Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row62Struct>) globalMap
						.get("tHash_Lookup_row62"));

				row62Struct row62HashKey = new row62Struct();
				row62Struct row62Default = new row62Struct();
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_49__Struct {
				}
				Var__tMap_49__Struct Var__tMap_49 = new Var__tMap_49__Struct();
// ###############################

// ###############################
// # Outputs initialization
				cStruct c_tmp = new cStruct();
// ###############################

				/**
				 * [tMap_49 begin ] stop
				 */

				/**
				 * [tMap_48 begin ] start
				 */

				ok_Hash.put("tMap_48", false);
				start_Hash.put("tMap_48", System.currentTimeMillis());

				currentComponent = "tMap_48";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row58");
				}

				int tos_count_tMap_48 = 0;

// ###############################
// # Lookup's keys initialization
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_48__Struct {
				}
				Var__tMap_48__Struct Var__tMap_48 = new Var__tMap_48__Struct();
// ###############################

// ###############################
// # Outputs initialization
				Dim_Reference_OSM_Opportunite_mobileStruct Dim_Reference_OSM_Opportunite_mobile_tmp = new Dim_Reference_OSM_Opportunite_mobileStruct();
				Dim_type_engagement_mobileStruct Dim_type_engagement_mobile_tmp = new Dim_type_engagement_mobileStruct();
				Extension_idStruct Extension_id_tmp = new Extension_idStruct();
				Dim_nature_offre_mobileStruct Dim_nature_offre_mobile_tmp = new Dim_nature_offre_mobileStruct();
				Dim_offre_mobileStruct Dim_offre_mobile_tmp = new Dim_offre_mobileStruct();
// ###############################

				/**
				 * [tMap_48 begin ] stop
				 */

				/**
				 * [tFileInputExcel_35 begin ] start
				 */

				ok_Hash.put("tFileInputExcel_35", false);
				start_Hash.put("tFileInputExcel_35", System.currentTimeMillis());

				currentComponent = "tFileInputExcel_35";

				int tos_count_tFileInputExcel_35 = 0;

				final String decryptedPassword_tFileInputExcel_35 = routines.system.PasswordEncryptUtil
						.decryptPassword("enc:routine.encryption.key.v1:NS/q1U6Vx5ecpudMGM4ISu75v/qTmNtsFq3/Ww==");
				String password_tFileInputExcel_35 = decryptedPassword_tFileInputExcel_35;
				if (password_tFileInputExcel_35.isEmpty()) {
					password_tFileInputExcel_35 = null;
				}
				class RegexUtil_tFileInputExcel_35 {

					public java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> getSheets(
							org.apache.poi.xssf.usermodel.XSSFWorkbook workbook, String oneSheetName,
							boolean useRegex) {

						java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> list = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();

						if (useRegex) {// this part process the regex issue

							java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(oneSheetName);
							for (org.apache.poi.ss.usermodel.Sheet sheet : workbook) {
								String sheetName = sheet.getSheetName();
								java.util.regex.Matcher matcher = pattern.matcher(sheetName);
								if (matcher.matches()) {
									if (sheet != null) {
										list.add((org.apache.poi.xssf.usermodel.XSSFSheet) sheet);
									}
								}
							}

						} else {
							org.apache.poi.xssf.usermodel.XSSFSheet sheet = (org.apache.poi.xssf.usermodel.XSSFSheet) workbook
									.getSheet(oneSheetName);
							if (sheet != null) {
								list.add(sheet);
							}

						}

						return list;
					}

					public java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> getSheets(
							org.apache.poi.xssf.usermodel.XSSFWorkbook workbook, int index, boolean useRegex) {
						java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> list = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
						org.apache.poi.xssf.usermodel.XSSFSheet sheet = (org.apache.poi.xssf.usermodel.XSSFSheet) workbook
								.getSheetAt(index);
						if (sheet != null) {
							list.add(sheet);
						}
						return list;
					}

				}
				RegexUtil_tFileInputExcel_35 regexUtil_tFileInputExcel_35 = new RegexUtil_tFileInputExcel_35();

				Object source_tFileInputExcel_35 = "C:/Users/DELL/Downloads/Copie_de_EXTRACT_OPPORTUNITES_MOBILE_BB_VF_090520222381_1ans.xlsx";
				org.apache.poi.xssf.usermodel.XSSFWorkbook workbook_tFileInputExcel_35 = null;

				if (source_tFileInputExcel_35 instanceof String) {
					workbook_tFileInputExcel_35 = (org.apache.poi.xssf.usermodel.XSSFWorkbook) org.apache.poi.ss.usermodel.WorkbookFactory
							.create(new java.io.File((String) source_tFileInputExcel_35), password_tFileInputExcel_35,
									true);
				} else if (source_tFileInputExcel_35 instanceof java.io.InputStream) {
					workbook_tFileInputExcel_35 = (org.apache.poi.xssf.usermodel.XSSFWorkbook) org.apache.poi.ss.usermodel.WorkbookFactory
							.create((java.io.InputStream) source_tFileInputExcel_35, password_tFileInputExcel_35);
				} else {
					workbook_tFileInputExcel_35 = null;
					throw new java.lang.Exception("The data source should be specified as Inputstream or File Path!");
				}
				try {

					java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> sheetList_tFileInputExcel_35 = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
					sheetList_tFileInputExcel_35.addAll(
							regexUtil_tFileInputExcel_35.getSheets(workbook_tFileInputExcel_35, "Source", false));
					if (sheetList_tFileInputExcel_35.size() <= 0) {
						throw new RuntimeException("Special sheets not exist!");
					}

					java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> sheetList_FilterNull_tFileInputExcel_35 = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
					for (org.apache.poi.xssf.usermodel.XSSFSheet sheet_FilterNull_tFileInputExcel_35 : sheetList_tFileInputExcel_35) {
						if (sheet_FilterNull_tFileInputExcel_35 != null
								&& sheetList_FilterNull_tFileInputExcel_35.iterator() != null
								&& sheet_FilterNull_tFileInputExcel_35.iterator().hasNext()) {
							sheetList_FilterNull_tFileInputExcel_35.add(sheet_FilterNull_tFileInputExcel_35);
						}
					}
					sheetList_tFileInputExcel_35 = sheetList_FilterNull_tFileInputExcel_35;
					if (sheetList_tFileInputExcel_35.size() > 0) {
						int nb_line_tFileInputExcel_35 = 0;

						int begin_line_tFileInputExcel_35 = 1;

						int footer_input_tFileInputExcel_35 = 0;

						int end_line_tFileInputExcel_35 = 0;
						for (org.apache.poi.xssf.usermodel.XSSFSheet sheet_tFileInputExcel_35 : sheetList_tFileInputExcel_35) {
							end_line_tFileInputExcel_35 += (sheet_tFileInputExcel_35.getLastRowNum() + 1);
						}
						end_line_tFileInputExcel_35 -= footer_input_tFileInputExcel_35;
						int limit_tFileInputExcel_35 = -1;
						int start_column_tFileInputExcel_35 = 1 - 1;
						int end_column_tFileInputExcel_35 = -1;

						org.apache.poi.xssf.usermodel.XSSFRow row_tFileInputExcel_35 = null;
						org.apache.poi.xssf.usermodel.XSSFSheet sheet_tFileInputExcel_35 = sheetList_tFileInputExcel_35
								.get(0);
						int rowCount_tFileInputExcel_35 = 0;
						int sheetIndex_tFileInputExcel_35 = 0;
						int currentRows_tFileInputExcel_35 = (sheetList_tFileInputExcel_35.get(0).getLastRowNum() + 1);

						// for the number format
						java.text.DecimalFormat df_tFileInputExcel_35 = new java.text.DecimalFormat(
								"#.####################################");
						char decimalChar_tFileInputExcel_35 = df_tFileInputExcel_35.getDecimalFormatSymbols()
								.getDecimalSeparator();

						for (int i_tFileInputExcel_35 = begin_line_tFileInputExcel_35; i_tFileInputExcel_35 < end_line_tFileInputExcel_35; i_tFileInputExcel_35++) {

							int emptyColumnCount_tFileInputExcel_35 = 0;

							if (limit_tFileInputExcel_35 != -1
									&& nb_line_tFileInputExcel_35 >= limit_tFileInputExcel_35) {
								break;
							}

							while (i_tFileInputExcel_35 >= rowCount_tFileInputExcel_35
									+ currentRows_tFileInputExcel_35) {
								rowCount_tFileInputExcel_35 += currentRows_tFileInputExcel_35;
								sheet_tFileInputExcel_35 = sheetList_tFileInputExcel_35
										.get(++sheetIndex_tFileInputExcel_35);
								currentRows_tFileInputExcel_35 = (sheet_tFileInputExcel_35.getLastRowNum() + 1);
							}
							globalMap.put("tFileInputExcel_35_CURRENT_SHEET", sheet_tFileInputExcel_35.getSheetName());
							if (rowCount_tFileInputExcel_35 <= i_tFileInputExcel_35) {
								row_tFileInputExcel_35 = sheet_tFileInputExcel_35
										.getRow(i_tFileInputExcel_35 - rowCount_tFileInputExcel_35);
							}
							row58 = null;
							int tempRowLength_tFileInputExcel_35 = 31;

							int columnIndex_tFileInputExcel_35 = 0;

							String[] temp_row_tFileInputExcel_35 = new String[tempRowLength_tFileInputExcel_35];
							int excel_end_column_tFileInputExcel_35;
							if (row_tFileInputExcel_35 == null) {
								excel_end_column_tFileInputExcel_35 = 0;
							} else {
								excel_end_column_tFileInputExcel_35 = row_tFileInputExcel_35.getLastCellNum();
							}
							int actual_end_column_tFileInputExcel_35;
							if (end_column_tFileInputExcel_35 == -1) {
								actual_end_column_tFileInputExcel_35 = excel_end_column_tFileInputExcel_35;
							} else {
								actual_end_column_tFileInputExcel_35 = end_column_tFileInputExcel_35 > excel_end_column_tFileInputExcel_35
										? excel_end_column_tFileInputExcel_35
										: end_column_tFileInputExcel_35;
							}
							org.apache.poi.ss.formula.eval.NumberEval ne_tFileInputExcel_35 = null;
							for (int i = 0; i < tempRowLength_tFileInputExcel_35; i++) {
								if (i + start_column_tFileInputExcel_35 < actual_end_column_tFileInputExcel_35) {
									org.apache.poi.ss.usermodel.Cell cell_tFileInputExcel_35 = row_tFileInputExcel_35
											.getCell(i + start_column_tFileInputExcel_35);
									if (cell_tFileInputExcel_35 != null) {
										switch (cell_tFileInputExcel_35.getCellType()) {
										case STRING:
											temp_row_tFileInputExcel_35[i] = cell_tFileInputExcel_35
													.getRichStringCellValue().getString();
											break;
										case NUMERIC:
											if (org.apache.poi.ss.usermodel.DateUtil
													.isCellDateFormatted(cell_tFileInputExcel_35)) {
												temp_row_tFileInputExcel_35[i] = cell_tFileInputExcel_35
														.getDateCellValue().toString();
											} else {
												temp_row_tFileInputExcel_35[i] = df_tFileInputExcel_35
														.format(cell_tFileInputExcel_35.getNumericCellValue());
											}
											break;
										case BOOLEAN:
											temp_row_tFileInputExcel_35[i] = String
													.valueOf(cell_tFileInputExcel_35.getBooleanCellValue());
											break;
										case FORMULA:
											switch (cell_tFileInputExcel_35.getCachedFormulaResultType()) {
											case STRING:
												temp_row_tFileInputExcel_35[i] = cell_tFileInputExcel_35
														.getRichStringCellValue().getString();
												break;
											case NUMERIC:
												if (org.apache.poi.ss.usermodel.DateUtil
														.isCellDateFormatted(cell_tFileInputExcel_35)) {
													temp_row_tFileInputExcel_35[i] = cell_tFileInputExcel_35
															.getDateCellValue().toString();
												} else {
													ne_tFileInputExcel_35 = new org.apache.poi.ss.formula.eval.NumberEval(
															cell_tFileInputExcel_35.getNumericCellValue());
													temp_row_tFileInputExcel_35[i] = ne_tFileInputExcel_35
															.getStringValue();
												}
												break;
											case BOOLEAN:
												temp_row_tFileInputExcel_35[i] = String
														.valueOf(cell_tFileInputExcel_35.getBooleanCellValue());
												break;
											default:
												temp_row_tFileInputExcel_35[i] = "";
											}
											break;
										default:
											temp_row_tFileInputExcel_35[i] = "";
										}
									} else {
										temp_row_tFileInputExcel_35[i] = "";
									}

								} else {
									temp_row_tFileInputExcel_35[i] = "";
								}
							}
							boolean whetherReject_tFileInputExcel_35 = false;
							row58 = new row58Struct();
							int curColNum_tFileInputExcel_35 = -1;
							String curColName_tFileInputExcel_35 = "";
							try {
								columnIndex_tFileInputExcel_35 = 0;

								if (temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35].length() > 0) {
									curColNum_tFileInputExcel_35 = columnIndex_tFileInputExcel_35
											+ start_column_tFileInputExcel_35 + 1;
									curColName_tFileInputExcel_35 = "Reference_OSM_mere__Opportunite";

									row58.Reference_OSM_mere__Opportunite = temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35];
								} else {
									row58.Reference_OSM_mere__Opportunite = null;
									emptyColumnCount_tFileInputExcel_35++;
								}
								columnIndex_tFileInputExcel_35 = 1;

								if (temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35].length() > 0) {
									curColNum_tFileInputExcel_35 = columnIndex_tFileInputExcel_35
											+ start_column_tFileInputExcel_35 + 1;
									curColName_tFileInputExcel_35 = "Reference_OSM__Opportunite";

									row58.Reference_OSM__Opportunite = temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35];
								} else {
									row58.Reference_OSM__Opportunite = null;
									emptyColumnCount_tFileInputExcel_35++;
								}
								columnIndex_tFileInputExcel_35 = 2;

								if (temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35].length() > 0) {
									curColNum_tFileInputExcel_35 = columnIndex_tFileInputExcel_35
											+ start_column_tFileInputExcel_35 + 1;
									curColName_tFileInputExcel_35 = "Client_Prospect__Opportunite";

									row58.Client_Prospect__Opportunite = temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35];
								} else {
									row58.Client_Prospect__Opportunite = null;
									emptyColumnCount_tFileInputExcel_35++;
								}
								columnIndex_tFileInputExcel_35 = 3;

								if (temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35].length() > 0) {
									curColNum_tFileInputExcel_35 = columnIndex_tFileInputExcel_35
											+ start_column_tFileInputExcel_35 + 1;
									curColName_tFileInputExcel_35 = "Offre";

									row58.Offre = temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35];
								} else {
									row58.Offre = null;
									emptyColumnCount_tFileInputExcel_35++;
								}
								columnIndex_tFileInputExcel_35 = 4;

								if (temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35].length() > 0) {
									curColNum_tFileInputExcel_35 = columnIndex_tFileInputExcel_35
											+ start_column_tFileInputExcel_35 + 1;
									curColName_tFileInputExcel_35 = "Nature_Offre";

									row58.Nature_Offre = temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35];
								} else {
									row58.Nature_Offre = null;
									emptyColumnCount_tFileInputExcel_35++;
								}
								columnIndex_tFileInputExcel_35 = 5;

								if (temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35].length() > 0) {
									curColNum_tFileInputExcel_35 = columnIndex_tFileInputExcel_35
											+ start_column_tFileInputExcel_35 + 1;
									curColName_tFileInputExcel_35 = "Produit_existant";

									row58.Produit_existant = temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35];
								} else {
									row58.Produit_existant = null;
									emptyColumnCount_tFileInputExcel_35++;
								}
								columnIndex_tFileInputExcel_35 = 6;

								if (temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35].length() > 0) {
									curColNum_tFileInputExcel_35 = columnIndex_tFileInputExcel_35
											+ start_column_tFileInputExcel_35 + 1;
									curColName_tFileInputExcel_35 = "Nature_produit__Produit_existant";

									row58.Nature_produit__Produit_existant = temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35];
								} else {
									row58.Nature_produit__Produit_existant = null;
									emptyColumnCount_tFileInputExcel_35++;
								}
								columnIndex_tFileInputExcel_35 = 7;

								if (temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35].length() > 0) {
									curColNum_tFileInputExcel_35 = columnIndex_tFileInputExcel_35
											+ start_column_tFileInputExcel_35 + 1;
									curColName_tFileInputExcel_35 = "Quantite";

									row58.Quantite = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35], null,
											'.' == decimalChar_tFileInputExcel_35 ? null
													: decimalChar_tFileInputExcel_35));
								} else {
									row58.Quantite = null;
									emptyColumnCount_tFileInputExcel_35++;
								}
								columnIndex_tFileInputExcel_35 = 8;

								if (temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35].length() > 0) {
									curColNum_tFileInputExcel_35 = columnIndex_tFileInputExcel_35
											+ start_column_tFileInputExcel_35 + 1;
									curColName_tFileInputExcel_35 = "Prix_unitaire";

									row58.Prix_unitaire = temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35];
								} else {
									row58.Prix_unitaire = null;
									emptyColumnCount_tFileInputExcel_35++;
								}
								columnIndex_tFileInputExcel_35 = 9;

								if (temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35].length() > 0) {
									curColNum_tFileInputExcel_35 = columnIndex_tFileInputExcel_35
											+ start_column_tFileInputExcel_35 + 1;
									curColName_tFileInputExcel_35 = "Total_final";

									row58.Total_final = temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35];
								} else {
									row58.Total_final = null;
									emptyColumnCount_tFileInputExcel_35++;
								}
								columnIndex_tFileInputExcel_35 = 10;

								if (temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35].length() > 0) {
									curColNum_tFileInputExcel_35 = columnIndex_tFileInputExcel_35
											+ start_column_tFileInputExcel_35 + 1;
									curColName_tFileInputExcel_35 = "Remise______Opportunite";

									row58.Remise______Opportunite = ParserUtils.parseTo_Float(ParserUtils
											.parseTo_Number(temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35],
													null, '.' == decimalChar_tFileInputExcel_35 ? null
															: decimalChar_tFileInputExcel_35));
								} else {
									row58.Remise______Opportunite = null;
									emptyColumnCount_tFileInputExcel_35++;
								}
								columnIndex_tFileInputExcel_35 = 11;

								if (temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35].length() > 0) {
									curColNum_tFileInputExcel_35 = columnIndex_tFileInputExcel_35
											+ start_column_tFileInputExcel_35 + 1;
									curColName_tFileInputExcel_35 = "Total_remise_HT";

									row58.Total_remise_HT = ParserUtils.parseTo_Float(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35], null,
											'.' == decimalChar_tFileInputExcel_35 ? null
													: decimalChar_tFileInputExcel_35));
								} else {
									row58.Total_remise_HT = null;
									emptyColumnCount_tFileInputExcel_35++;
								}
								columnIndex_tFileInputExcel_35 = 12;

								if (temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35].length() > 0) {
									curColNum_tFileInputExcel_35 = columnIndex_tFileInputExcel_35
											+ start_column_tFileInputExcel_35 + 1;
									curColName_tFileInputExcel_35 = "Extension__Opportunite";

									row58.Extension__Opportunite = temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35];
								} else {
									row58.Extension__Opportunite = null;
									emptyColumnCount_tFileInputExcel_35++;
								}
								columnIndex_tFileInputExcel_35 = 13;

								if (temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35].length() > 0) {
									curColNum_tFileInputExcel_35 = columnIndex_tFileInputExcel_35
											+ start_column_tFileInputExcel_35 + 1;
									curColName_tFileInputExcel_35 = "Budget_a_valider__Opportunite";

									row58.Budget_a_valider__Opportunite = temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35];
								} else {
									row58.Budget_a_valider__Opportunite = null;
									emptyColumnCount_tFileInputExcel_35++;
								}
								columnIndex_tFileInputExcel_35 = 14;

								if (temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35].length() > 0) {
									curColNum_tFileInputExcel_35 = columnIndex_tFileInputExcel_35
											+ start_column_tFileInputExcel_35 + 1;
									curColName_tFileInputExcel_35 = "Budget_a_valider_Extension__Opportunite";

									row58.Budget_a_valider_Extension__Opportunite = temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35];
								} else {
									row58.Budget_a_valider_Extension__Opportunite = null;
									emptyColumnCount_tFileInputExcel_35++;
								}
								columnIndex_tFileInputExcel_35 = 15;

								if (temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35].length() > 0) {
									curColNum_tFileInputExcel_35 = columnIndex_tFileInputExcel_35
											+ start_column_tFileInputExcel_35 + 1;
									curColName_tFileInputExcel_35 = "Duree_d_engagement__Opportunite";

									row58.Duree_d_engagement__Opportunite = temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35];
								} else {
									row58.Duree_d_engagement__Opportunite = null;
									emptyColumnCount_tFileInputExcel_35++;
								}
								columnIndex_tFileInputExcel_35 = 16;

								if (temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35].length() > 0) {
									curColNum_tFileInputExcel_35 = columnIndex_tFileInputExcel_35
											+ start_column_tFileInputExcel_35 + 1;
									curColName_tFileInputExcel_35 = "Type_d_engagement__Opportunite";

									row58.Type_d_engagement__Opportunite = temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35];
								} else {
									row58.Type_d_engagement__Opportunite = null;
									emptyColumnCount_tFileInputExcel_35++;
								}
								columnIndex_tFileInputExcel_35 = 17;

								if (temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35].length() > 0) {
									curColNum_tFileInputExcel_35 = columnIndex_tFileInputExcel_35
											+ start_column_tFileInputExcel_35 + 1;
									curColName_tFileInputExcel_35 = "Demandeur__Opportunite";

									row58.Demandeur__Opportunite = temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35];
								} else {
									row58.Demandeur__Opportunite = null;
									emptyColumnCount_tFileInputExcel_35++;
								}
								columnIndex_tFileInputExcel_35 = 18;

								if (temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35].length() > 0) {
									curColNum_tFileInputExcel_35 = columnIndex_tFileInputExcel_35
											+ start_column_tFileInputExcel_35 + 1;
									curColName_tFileInputExcel_35 = "Equipe";

									row58.Equipe = temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35];
								} else {
									row58.Equipe = null;
									emptyColumnCount_tFileInputExcel_35++;
								}
								columnIndex_tFileInputExcel_35 = 19;

								if (temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35].length() > 0) {
									curColNum_tFileInputExcel_35 = columnIndex_tFileInputExcel_35
											+ start_column_tFileInputExcel_35 + 1;
									curColName_tFileInputExcel_35 = "Cree_le__Opportunite";

									row58.Cree_le__Opportunite = temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35];
								} else {
									row58.Cree_le__Opportunite = null;
									emptyColumnCount_tFileInputExcel_35++;
								}
								columnIndex_tFileInputExcel_35 = 20;

								if (temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35].length() > 0) {
									curColNum_tFileInputExcel_35 = columnIndex_tFileInputExcel_35
											+ start_column_tFileInputExcel_35 + 1;
									curColName_tFileInputExcel_35 = "Mois_de_creation";

									row58.Mois_de_creation = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35], null,
											'.' == decimalChar_tFileInputExcel_35 ? null
													: decimalChar_tFileInputExcel_35));
								} else {
									row58.Mois_de_creation = null;
									emptyColumnCount_tFileInputExcel_35++;
								}
								columnIndex_tFileInputExcel_35 = 21;

								if (temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35].length() > 0) {
									curColNum_tFileInputExcel_35 = columnIndex_tFileInputExcel_35
											+ start_column_tFileInputExcel_35 + 1;
									curColName_tFileInputExcel_35 = "Annee_de_creation";

									row58.Annee_de_creation = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35], null,
											'.' == decimalChar_tFileInputExcel_35 ? null
													: decimalChar_tFileInputExcel_35));
								} else {
									row58.Annee_de_creation = null;
									emptyColumnCount_tFileInputExcel_35++;
								}
								columnIndex_tFileInputExcel_35 = 22;

								if (temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35].length() > 0) {
									curColNum_tFileInputExcel_35 = columnIndex_tFileInputExcel_35
											+ start_column_tFileInputExcel_35 + 1;
									curColName_tFileInputExcel_35 = "Mois_et_annee_de_creation";

									row58.Mois_et_annee_de_creation = temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35];
								} else {
									row58.Mois_et_annee_de_creation = null;
									emptyColumnCount_tFileInputExcel_35++;
								}
								columnIndex_tFileInputExcel_35 = 23;

								if (temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35].length() > 0) {
									curColNum_tFileInputExcel_35 = columnIndex_tFileInputExcel_35
											+ start_column_tFileInputExcel_35 + 1;
									curColName_tFileInputExcel_35 = "Raison_de_statut_OSM__Opportunite";

									row58.Raison_de_statut_OSM__Opportunite = temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35];
								} else {
									row58.Raison_de_statut_OSM__Opportunite = null;
									emptyColumnCount_tFileInputExcel_35++;
								}
								columnIndex_tFileInputExcel_35 = 24;

								if (temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35].length() > 0) {
									curColNum_tFileInputExcel_35 = columnIndex_tFileInputExcel_35
											+ start_column_tFileInputExcel_35 + 1;
									curColName_tFileInputExcel_35 = "Statut_OSM__Opportunite";

									row58.Statut_OSM__Opportunite = temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35];
								} else {
									row58.Statut_OSM__Opportunite = null;
									emptyColumnCount_tFileInputExcel_35++;
								}
								columnIndex_tFileInputExcel_35 = 25;

								if (temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35].length() > 0) {
									curColNum_tFileInputExcel_35 = columnIndex_tFileInputExcel_35
											+ start_column_tFileInputExcel_35 + 1;
									curColName_tFileInputExcel_35 = "Statut_activation__Commande";

									row58.Statut_activation__Commande = temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35];
								} else {
									row58.Statut_activation__Commande = null;
									emptyColumnCount_tFileInputExcel_35++;
								}
								columnIndex_tFileInputExcel_35 = 26;

								if (temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35].length() > 0) {
									curColNum_tFileInputExcel_35 = columnIndex_tFileInputExcel_35
											+ start_column_tFileInputExcel_35 + 1;
									curColName_tFileInputExcel_35 = "Date_d_activation__Commande";

									row58.Date_d_activation__Commande = temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35];
								} else {
									row58.Date_d_activation__Commande = null;
									emptyColumnCount_tFileInputExcel_35++;
								}
								columnIndex_tFileInputExcel_35 = 27;

								if (temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35].length() > 0) {
									curColNum_tFileInputExcel_35 = columnIndex_tFileInputExcel_35
											+ start_column_tFileInputExcel_35 + 1;
									curColName_tFileInputExcel_35 = "Mois_d_activation";

									row58.Mois_d_activation = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35], null,
											'.' == decimalChar_tFileInputExcel_35 ? null
													: decimalChar_tFileInputExcel_35));
								} else {
									row58.Mois_d_activation = null;
									emptyColumnCount_tFileInputExcel_35++;
								}
								columnIndex_tFileInputExcel_35 = 28;

								if (temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35].length() > 0) {
									curColNum_tFileInputExcel_35 = columnIndex_tFileInputExcel_35
											+ start_column_tFileInputExcel_35 + 1;
									curColName_tFileInputExcel_35 = "Staut_Final";

									row58.Staut_Final = temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35];
								} else {
									row58.Staut_Final = null;
									emptyColumnCount_tFileInputExcel_35++;
								}
								columnIndex_tFileInputExcel_35 = 29;

								if (temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35].length() > 0) {
									curColNum_tFileInputExcel_35 = columnIndex_tFileInputExcel_35
											+ start_column_tFileInputExcel_35 + 1;
									curColName_tFileInputExcel_35 = "Gouvernorat";

									row58.Gouvernorat = temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35];
								} else {
									row58.Gouvernorat = null;
									emptyColumnCount_tFileInputExcel_35++;
								}
								columnIndex_tFileInputExcel_35 = 30;

								if (temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35].length() > 0) {
									curColNum_tFileInputExcel_35 = columnIndex_tFileInputExcel_35
											+ start_column_tFileInputExcel_35 + 1;
									curColName_tFileInputExcel_35 = "Phase_de_l_opportunite__Opportunite";

									row58.Phase_de_l_opportunite__Opportunite = temp_row_tFileInputExcel_35[columnIndex_tFileInputExcel_35];
								} else {
									row58.Phase_de_l_opportunite__Opportunite = null;
									emptyColumnCount_tFileInputExcel_35++;
								}

								nb_line_tFileInputExcel_35++;

							} catch (java.lang.Exception e) {
								globalMap.put("tFileInputExcel_35_ERROR_MESSAGE", e.getMessage());
								whetherReject_tFileInputExcel_35 = true;
								System.err.println(e.getMessage());
								row58 = null;
							}

							/**
							 * [tFileInputExcel_35 begin ] stop
							 */

							/**
							 * [tFileInputExcel_35 main ] start
							 */

							currentComponent = "tFileInputExcel_35";

							tos_count_tFileInputExcel_35++;

							/**
							 * [tFileInputExcel_35 main ] stop
							 */

							/**
							 * [tFileInputExcel_35 process_data_begin ] start
							 */

							currentComponent = "tFileInputExcel_35";

							/**
							 * [tFileInputExcel_35 process_data_begin ] stop
							 */
// Start of branch "row58"
							if (row58 != null) {

								/**
								 * [tMap_48 main ] start
								 */

								currentComponent = "tMap_48";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "row58"

									);
								}

								boolean hasCasePrimitiveKeyWithNull_tMap_48 = false;

								// ###############################
								// # Input tables (lookups)
								boolean rejectedInnerJoin_tMap_48 = false;
								boolean mainRowRejected_tMap_48 = false;

								// ###############################
								{ // start of Var scope

									// ###############################
									// # Vars tables

									Var__tMap_48__Struct Var = Var__tMap_48;// ###############################
									// ###############################
									// # Output tables

									Dim_Reference_OSM_Opportunite_mobile = null;
									Dim_type_engagement_mobile = null;
									Extension_id = null;
									Dim_nature_offre_mobile = null;
									Dim_offre_mobile = null;

// # Output table : 'Dim_Reference_OSM_Opportunite_mobile'
									Dim_Reference_OSM_Opportunite_mobile_tmp.Reference_OSM__Opportunite = row58.Reference_OSM__Opportunite;
									Dim_Reference_OSM_Opportunite_mobile = Dim_Reference_OSM_Opportunite_mobile_tmp;

// # Output table : 'Dim_type_engagement_mobile'
									Dim_type_engagement_mobile_tmp.Type_d_engagement__Opportunite = row58.Type_d_engagement__Opportunite;
									Dim_type_engagement_mobile = Dim_type_engagement_mobile_tmp;

// # Output table : 'Extension_id'
									Extension_id_tmp.Extension__Opportunite = row58.Extension__Opportunite;
									Extension_id = Extension_id_tmp;

// # Output table : 'Dim_nature_offre_mobile'
									Dim_nature_offre_mobile_tmp.Nature_Offre = row58.Nature_Offre;
									Dim_nature_offre_mobile = Dim_nature_offre_mobile_tmp;

// # Output table : 'Dim_offre_mobile'
									Dim_offre_mobile_tmp.Offre = row58.Offre;
									Dim_offre_mobile = Dim_offre_mobile_tmp;
// ###############################

								} // end of Var scope

								rejectedInnerJoin_tMap_48 = false;

								tos_count_tMap_48++;

								/**
								 * [tMap_48 main ] stop
								 */

								/**
								 * [tMap_48 process_data_begin ] start
								 */

								currentComponent = "tMap_48";

								/**
								 * [tMap_48 process_data_begin ] stop
								 */
// Start of branch "Dim_Reference_OSM_Opportunite_mobile"
								if (Dim_Reference_OSM_Opportunite_mobile != null) {

									/**
									 * [tMap_51 main ] start
									 */

									currentComponent = "tMap_51";

									if (execStat) {
										runStat.updateStatOnConnection(iterateId, 1, 1

												, "Dim_Reference_OSM_Opportunite_mobile"

										);
									}

									boolean hasCasePrimitiveKeyWithNull_tMap_51 = false;

									// ###############################
									// # Input tables (lookups)
									boolean rejectedInnerJoin_tMap_51 = false;
									boolean mainRowRejected_tMap_51 = false;

									///////////////////////////////////////////////
									// Starting Lookup Table "row67"
									///////////////////////////////////////////////

									boolean forceLooprow67 = false;

									row67Struct row67ObjectFromLookup = null;

									if (!rejectedInnerJoin_tMap_51) { // G_TM_M_020

										hasCasePrimitiveKeyWithNull_tMap_51 = false;

										row67HashKey.Reference_OSM__Opportunite = Dim_Reference_OSM_Opportunite_mobile.Reference_OSM__Opportunite;

										row67HashKey.hashCodeDirty = true;

										tHash_Lookup_row67.lookup(row67HashKey);

										if (!tHash_Lookup_row67.hasNext()) { // G_TM_M_090

											rejectedInnerJoin_tMap_51 = true;

										} // G_TM_M_090

									} // G_TM_M_020

									if (tHash_Lookup_row67 != null && tHash_Lookup_row67.getCount(row67HashKey) > 1) { // G
																														// 071

										// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup
										// 'row67' and it contains more one result from keys :
										// row67.Reference_OSM__Opportunite = '" +
										// row67HashKey.Reference_OSM__Opportunite + "'");
									} // G 071

									row67Struct row67 = null;

									row67Struct fromLookup_row67 = null;
									row67 = row67Default;

									if (tHash_Lookup_row67 != null && tHash_Lookup_row67.hasNext()) { // G 099

										fromLookup_row67 = tHash_Lookup_row67.next();

									} // G 099

									if (fromLookup_row67 != null) {
										row67 = fromLookup_row67;
									}

									// ###############################
									{ // start of Var scope

										// ###############################
										// # Vars tables

										Var__tMap_51__Struct Var = Var__tMap_51;// ###############################
										// ###############################
										// # Output tables

										b = null;

										if (!rejectedInnerJoin_tMap_51) {
										} // closing inner join bracket (1)
// ###### START REJECTS ##### 

// # Output reject table : 'b'
// # Filter conditions 
										if (rejectedInnerJoin_tMap_51) {
											b_tmp.Reference_OSM__Opportunite = Dim_Reference_OSM_Opportunite_mobile.Reference_OSM__Opportunite;
											b = b_tmp;
										} // closing filter/reject
// ###############################

									} // end of Var scope

									rejectedInnerJoin_tMap_51 = false;

									tos_count_tMap_51++;

									/**
									 * [tMap_51 main ] stop
									 */

									/**
									 * [tMap_51 process_data_begin ] start
									 */

									currentComponent = "tMap_51";

									/**
									 * [tMap_51 process_data_begin ] stop
									 */
// Start of branch "b"
									if (b != null) {

										/**
										 * [tUniqRow_15 main ] start
										 */

										currentComponent = "tUniqRow_15";

										if (execStat) {
											runStat.updateStatOnConnection(iterateId, 1, 1

													, "b"

											);
										}

										row68 = null;
										finder_tUniqRow_15.Reference_OSM__Opportunite = b.Reference_OSM__Opportunite;
										finder_tUniqRow_15.hashCodeDirty = true;
										if (!keystUniqRow_15.contains(finder_tUniqRow_15)) {
											KeyStruct_tUniqRow_15 new_tUniqRow_15 = new KeyStruct_tUniqRow_15();

											new_tUniqRow_15.Reference_OSM__Opportunite = b.Reference_OSM__Opportunite;

											keystUniqRow_15.add(new_tUniqRow_15);
											if (row68 == null) {

												row68 = new row68Struct();
											}
											row68.Reference_OSM__Opportunite = b.Reference_OSM__Opportunite;
											nb_uniques_tUniqRow_15++;
										} else {
											nb_duplicates_tUniqRow_15++;
										}

										tos_count_tUniqRow_15++;

										/**
										 * [tUniqRow_15 main ] stop
										 */

										/**
										 * [tUniqRow_15 process_data_begin ] start
										 */

										currentComponent = "tUniqRow_15";

										/**
										 * [tUniqRow_15 process_data_begin ] stop
										 */
// Start of branch "row68"
										if (row68 != null) {

											/**
											 * [tDBOutput_16 main ] start
											 */

											currentComponent = "tDBOutput_16";

											if (execStat) {
												runStat.updateStatOnConnection(iterateId, 1, 1

														, "row68"

												);
											}

											whetherReject_tDBOutput_16 = false;
											if (row68.Reference_OSM__Opportunite == null) {
												pstmt_tDBOutput_16.setNull(1, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_16.setString(1, row68.Reference_OSM__Opportunite);
											}

											pstmt_tDBOutput_16.addBatch();
											nb_line_tDBOutput_16++;

											batchSizeCounter_tDBOutput_16++;

											////////// batch execute by batch size///////
											class LimitBytesHelper_tDBOutput_16 {
												public int limitBytePart1(int counter,
														java.sql.PreparedStatement pstmt_tDBOutput_16)
														throws Exception {
													try {

														for (int countEach_tDBOutput_16 : pstmt_tDBOutput_16
																.executeBatch()) {
															if (countEach_tDBOutput_16 == -2
																	|| countEach_tDBOutput_16 == -3) {
																break;
															}
															counter += countEach_tDBOutput_16;
														}

													} catch (java.sql.BatchUpdateException e) {
														globalMap.put("tDBOutput_16_ERROR_MESSAGE", e.getMessage());

														int countSum_tDBOutput_16 = 0;
														for (int countEach_tDBOutput_16 : e.getUpdateCounts()) {
															counter += (countEach_tDBOutput_16 < 0 ? 0
																	: countEach_tDBOutput_16);
														}

														System.err.println(e.getMessage());

													}
													return counter;
												}

												public int limitBytePart2(int counter,
														java.sql.PreparedStatement pstmt_tDBOutput_16)
														throws Exception {
													try {

														for (int countEach_tDBOutput_16 : pstmt_tDBOutput_16
																.executeBatch()) {
															if (countEach_tDBOutput_16 == -2
																	|| countEach_tDBOutput_16 == -3) {
																break;
															}
															counter += countEach_tDBOutput_16;
														}

													} catch (java.sql.BatchUpdateException e) {
														globalMap.put("tDBOutput_16_ERROR_MESSAGE", e.getMessage());

														for (int countEach_tDBOutput_16 : e.getUpdateCounts()) {
															counter += (countEach_tDBOutput_16 < 0 ? 0
																	: countEach_tDBOutput_16);
														}

														System.err.println(e.getMessage());

													}
													return counter;
												}
											}
											if ((batchSize_tDBOutput_16 > 0)
													&& (batchSize_tDBOutput_16 <= batchSizeCounter_tDBOutput_16)) {

												insertedCount_tDBOutput_16 = new LimitBytesHelper_tDBOutput_16()
														.limitBytePart1(insertedCount_tDBOutput_16, pstmt_tDBOutput_16);
												rowsToCommitCount_tDBOutput_16 = insertedCount_tDBOutput_16;

												batchSizeCounter_tDBOutput_16 = 0;
											}

											//////////// commit every////////////

											commitCounter_tDBOutput_16++;
											if (commitEvery_tDBOutput_16 <= commitCounter_tDBOutput_16) {
												if ((batchSize_tDBOutput_16 > 0)
														&& (batchSizeCounter_tDBOutput_16 > 0)) {

													insertedCount_tDBOutput_16 = new LimitBytesHelper_tDBOutput_16()
															.limitBytePart1(insertedCount_tDBOutput_16,
																	pstmt_tDBOutput_16);

													batchSizeCounter_tDBOutput_16 = 0;
												}
												if (rowsToCommitCount_tDBOutput_16 != 0) {

												}
												conn_tDBOutput_16.commit();
												if (rowsToCommitCount_tDBOutput_16 != 0) {

													rowsToCommitCount_tDBOutput_16 = 0;
												}
												commitCounter_tDBOutput_16 = 0;
											}

											tos_count_tDBOutput_16++;

											/**
											 * [tDBOutput_16 main ] stop
											 */

											/**
											 * [tDBOutput_16 process_data_begin ] start
											 */

											currentComponent = "tDBOutput_16";

											/**
											 * [tDBOutput_16 process_data_begin ] stop
											 */

											/**
											 * [tDBOutput_16 process_data_end ] start
											 */

											currentComponent = "tDBOutput_16";

											/**
											 * [tDBOutput_16 process_data_end ] stop
											 */

										} // End of branch "row68"

										/**
										 * [tUniqRow_15 process_data_end ] start
										 */

										currentComponent = "tUniqRow_15";

										/**
										 * [tUniqRow_15 process_data_end ] stop
										 */

									} // End of branch "b"

									/**
									 * [tMap_51 process_data_end ] start
									 */

									currentComponent = "tMap_51";

									/**
									 * [tMap_51 process_data_end ] stop
									 */

								} // End of branch "Dim_Reference_OSM_Opportunite_mobile"

// Start of branch "Dim_type_engagement_mobile"
								if (Dim_type_engagement_mobile != null) {

									/**
									 * [tMap_52 main ] start
									 */

									currentComponent = "tMap_52";

									if (execStat) {
										runStat.updateStatOnConnection(iterateId, 1, 1

												, "Dim_type_engagement_mobile"

										);
									}

									boolean hasCasePrimitiveKeyWithNull_tMap_52 = false;

									// ###############################
									// # Input tables (lookups)
									boolean rejectedInnerJoin_tMap_52 = false;
									boolean mainRowRejected_tMap_52 = false;

									///////////////////////////////////////////////
									// Starting Lookup Table "row69"
									///////////////////////////////////////////////

									boolean forceLooprow69 = false;

									row69Struct row69ObjectFromLookup = null;

									if (!rejectedInnerJoin_tMap_52) { // G_TM_M_020

										hasCasePrimitiveKeyWithNull_tMap_52 = false;

										row69HashKey.Type_d_engagement__Opportunite = Dim_type_engagement_mobile.Type_d_engagement__Opportunite;

										row69HashKey.hashCodeDirty = true;

										tHash_Lookup_row69.lookup(row69HashKey);

										if (!tHash_Lookup_row69.hasNext()) { // G_TM_M_090

											rejectedInnerJoin_tMap_52 = true;

										} // G_TM_M_090

									} // G_TM_M_020

									if (tHash_Lookup_row69 != null && tHash_Lookup_row69.getCount(row69HashKey) > 1) { // G
																														// 071

										// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup
										// 'row69' and it contains more one result from keys :
										// row69.Type_d_engagement__Opportunite = '" +
										// row69HashKey.Type_d_engagement__Opportunite + "'");
									} // G 071

									row69Struct row69 = null;

									row69Struct fromLookup_row69 = null;
									row69 = row69Default;

									if (tHash_Lookup_row69 != null && tHash_Lookup_row69.hasNext()) { // G 099

										fromLookup_row69 = tHash_Lookup_row69.next();

									} // G 099

									if (fromLookup_row69 != null) {
										row69 = fromLookup_row69;
									}

									// ###############################
									{ // start of Var scope

										// ###############################
										// # Vars tables

										Var__tMap_52__Struct Var = Var__tMap_52;// ###############################
										// ###############################
										// # Output tables

										n = null;

										if (!rejectedInnerJoin_tMap_52) {
										} // closing inner join bracket (1)
// ###### START REJECTS ##### 

// # Output reject table : 'n'
// # Filter conditions 
										if (rejectedInnerJoin_tMap_52) {
											n_tmp.Type_d_engagement__Opportunite = Dim_type_engagement_mobile.Type_d_engagement__Opportunite;
											n = n_tmp;
										} // closing filter/reject
// ###############################

									} // end of Var scope

									rejectedInnerJoin_tMap_52 = false;

									tos_count_tMap_52++;

									/**
									 * [tMap_52 main ] stop
									 */

									/**
									 * [tMap_52 process_data_begin ] start
									 */

									currentComponent = "tMap_52";

									/**
									 * [tMap_52 process_data_begin ] stop
									 */
// Start of branch "n"
									if (n != null) {

										/**
										 * [tUniqRow_16 main ] start
										 */

										currentComponent = "tUniqRow_16";

										if (execStat) {
											runStat.updateStatOnConnection(iterateId, 1, 1

													, "n"

											);
										}

										row70 = null;
										finder_tUniqRow_16.Type_d_engagement__Opportunite = n.Type_d_engagement__Opportunite;
										finder_tUniqRow_16.hashCodeDirty = true;
										if (!keystUniqRow_16.contains(finder_tUniqRow_16)) {
											KeyStruct_tUniqRow_16 new_tUniqRow_16 = new KeyStruct_tUniqRow_16();

											new_tUniqRow_16.Type_d_engagement__Opportunite = n.Type_d_engagement__Opportunite;

											keystUniqRow_16.add(new_tUniqRow_16);
											if (row70 == null) {

												row70 = new row70Struct();
											}
											row70.Type_d_engagement__Opportunite = n.Type_d_engagement__Opportunite;
											nb_uniques_tUniqRow_16++;
										} else {
											nb_duplicates_tUniqRow_16++;
										}

										tos_count_tUniqRow_16++;

										/**
										 * [tUniqRow_16 main ] stop
										 */

										/**
										 * [tUniqRow_16 process_data_begin ] start
										 */

										currentComponent = "tUniqRow_16";

										/**
										 * [tUniqRow_16 process_data_begin ] stop
										 */
// Start of branch "row70"
										if (row70 != null) {

											/**
											 * [tDBOutput_17 main ] start
											 */

											currentComponent = "tDBOutput_17";

											if (execStat) {
												runStat.updateStatOnConnection(iterateId, 1, 1

														, "row70"

												);
											}

											whetherReject_tDBOutput_17 = false;
											if (row70.Type_d_engagement__Opportunite == null) {
												pstmt_tDBOutput_17.setNull(1, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_17.setString(1, row70.Type_d_engagement__Opportunite);
											}

											pstmt_tDBOutput_17.addBatch();
											nb_line_tDBOutput_17++;

											batchSizeCounter_tDBOutput_17++;

											////////// batch execute by batch size///////
											class LimitBytesHelper_tDBOutput_17 {
												public int limitBytePart1(int counter,
														java.sql.PreparedStatement pstmt_tDBOutput_17)
														throws Exception {
													try {

														for (int countEach_tDBOutput_17 : pstmt_tDBOutput_17
																.executeBatch()) {
															if (countEach_tDBOutput_17 == -2
																	|| countEach_tDBOutput_17 == -3) {
																break;
															}
															counter += countEach_tDBOutput_17;
														}

													} catch (java.sql.BatchUpdateException e) {
														globalMap.put("tDBOutput_17_ERROR_MESSAGE", e.getMessage());

														int countSum_tDBOutput_17 = 0;
														for (int countEach_tDBOutput_17 : e.getUpdateCounts()) {
															counter += (countEach_tDBOutput_17 < 0 ? 0
																	: countEach_tDBOutput_17);
														}

														System.err.println(e.getMessage());

													}
													return counter;
												}

												public int limitBytePart2(int counter,
														java.sql.PreparedStatement pstmt_tDBOutput_17)
														throws Exception {
													try {

														for (int countEach_tDBOutput_17 : pstmt_tDBOutput_17
																.executeBatch()) {
															if (countEach_tDBOutput_17 == -2
																	|| countEach_tDBOutput_17 == -3) {
																break;
															}
															counter += countEach_tDBOutput_17;
														}

													} catch (java.sql.BatchUpdateException e) {
														globalMap.put("tDBOutput_17_ERROR_MESSAGE", e.getMessage());

														for (int countEach_tDBOutput_17 : e.getUpdateCounts()) {
															counter += (countEach_tDBOutput_17 < 0 ? 0
																	: countEach_tDBOutput_17);
														}

														System.err.println(e.getMessage());

													}
													return counter;
												}
											}
											if ((batchSize_tDBOutput_17 > 0)
													&& (batchSize_tDBOutput_17 <= batchSizeCounter_tDBOutput_17)) {

												insertedCount_tDBOutput_17 = new LimitBytesHelper_tDBOutput_17()
														.limitBytePart1(insertedCount_tDBOutput_17, pstmt_tDBOutput_17);
												rowsToCommitCount_tDBOutput_17 = insertedCount_tDBOutput_17;

												batchSizeCounter_tDBOutput_17 = 0;
											}

											//////////// commit every////////////

											commitCounter_tDBOutput_17++;
											if (commitEvery_tDBOutput_17 <= commitCounter_tDBOutput_17) {
												if ((batchSize_tDBOutput_17 > 0)
														&& (batchSizeCounter_tDBOutput_17 > 0)) {

													insertedCount_tDBOutput_17 = new LimitBytesHelper_tDBOutput_17()
															.limitBytePart1(insertedCount_tDBOutput_17,
																	pstmt_tDBOutput_17);

													batchSizeCounter_tDBOutput_17 = 0;
												}
												if (rowsToCommitCount_tDBOutput_17 != 0) {

												}
												conn_tDBOutput_17.commit();
												if (rowsToCommitCount_tDBOutput_17 != 0) {

													rowsToCommitCount_tDBOutput_17 = 0;
												}
												commitCounter_tDBOutput_17 = 0;
											}

											tos_count_tDBOutput_17++;

											/**
											 * [tDBOutput_17 main ] stop
											 */

											/**
											 * [tDBOutput_17 process_data_begin ] start
											 */

											currentComponent = "tDBOutput_17";

											/**
											 * [tDBOutput_17 process_data_begin ] stop
											 */

											/**
											 * [tDBOutput_17 process_data_end ] start
											 */

											currentComponent = "tDBOutput_17";

											/**
											 * [tDBOutput_17 process_data_end ] stop
											 */

										} // End of branch "row70"

										/**
										 * [tUniqRow_16 process_data_end ] start
										 */

										currentComponent = "tUniqRow_16";

										/**
										 * [tUniqRow_16 process_data_end ] stop
										 */

									} // End of branch "n"

									/**
									 * [tMap_52 process_data_end ] start
									 */

									currentComponent = "tMap_52";

									/**
									 * [tMap_52 process_data_end ] stop
									 */

								} // End of branch "Dim_type_engagement_mobile"

// Start of branch "Extension_id"
								if (Extension_id != null) {

									/**
									 * [tMap_53 main ] start
									 */

									currentComponent = "tMap_53";

									if (execStat) {
										runStat.updateStatOnConnection(iterateId, 1, 1

												, "Extension_id"

										);
									}

									boolean hasCasePrimitiveKeyWithNull_tMap_53 = false;

									// ###############################
									// # Input tables (lookups)
									boolean rejectedInnerJoin_tMap_53 = false;
									boolean mainRowRejected_tMap_53 = false;

									///////////////////////////////////////////////
									// Starting Lookup Table "row72"
									///////////////////////////////////////////////

									boolean forceLooprow72 = false;

									row72Struct row72ObjectFromLookup = null;

									if (!rejectedInnerJoin_tMap_53) { // G_TM_M_020

										hasCasePrimitiveKeyWithNull_tMap_53 = false;

										row72HashKey.Extension__Opportunite = Extension_id.Extension__Opportunite;

										row72HashKey.hashCodeDirty = true;

										tHash_Lookup_row72.lookup(row72HashKey);

										if (!tHash_Lookup_row72.hasNext()) { // G_TM_M_090

											rejectedInnerJoin_tMap_53 = true;

										} // G_TM_M_090

									} // G_TM_M_020

									if (tHash_Lookup_row72 != null && tHash_Lookup_row72.getCount(row72HashKey) > 1) { // G
																														// 071

										// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup
										// 'row72' and it contains more one result from keys :
										// row72.Extension__Opportunite = '" + row72HashKey.Extension__Opportunite +
										// "'");
									} // G 071

									row72Struct row72 = null;

									row72Struct fromLookup_row72 = null;
									row72 = row72Default;

									if (tHash_Lookup_row72 != null && tHash_Lookup_row72.hasNext()) { // G 099

										fromLookup_row72 = tHash_Lookup_row72.next();

									} // G 099

									if (fromLookup_row72 != null) {
										row72 = fromLookup_row72;
									}

									// ###############################
									{ // start of Var scope

										// ###############################
										// # Vars tables

										Var__tMap_53__Struct Var = Var__tMap_53;// ###############################
										// ###############################
										// # Output tables

										q = null;

										if (!rejectedInnerJoin_tMap_53) {
										} // closing inner join bracket (1)
// ###### START REJECTS ##### 

// # Output reject table : 'q'
// # Filter conditions 
										if (rejectedInnerJoin_tMap_53) {
											q_tmp.Extension__Opportunite = Extension_id.Extension__Opportunite;
											q = q_tmp;
										} // closing filter/reject
// ###############################

									} // end of Var scope

									rejectedInnerJoin_tMap_53 = false;

									tos_count_tMap_53++;

									/**
									 * [tMap_53 main ] stop
									 */

									/**
									 * [tMap_53 process_data_begin ] start
									 */

									currentComponent = "tMap_53";

									/**
									 * [tMap_53 process_data_begin ] stop
									 */
// Start of branch "q"
									if (q != null) {

										/**
										 * [tUniqRow_17 main ] start
										 */

										currentComponent = "tUniqRow_17";

										if (execStat) {
											runStat.updateStatOnConnection(iterateId, 1, 1

													, "q"

											);
										}

										row71 = null;
										finder_tUniqRow_17.Extension__Opportunite = q.Extension__Opportunite;
										finder_tUniqRow_17.hashCodeDirty = true;
										if (!keystUniqRow_17.contains(finder_tUniqRow_17)) {
											KeyStruct_tUniqRow_17 new_tUniqRow_17 = new KeyStruct_tUniqRow_17();

											new_tUniqRow_17.Extension__Opportunite = q.Extension__Opportunite;

											keystUniqRow_17.add(new_tUniqRow_17);
											if (row71 == null) {

												row71 = new row71Struct();
											}
											row71.Extension__Opportunite = q.Extension__Opportunite;
											nb_uniques_tUniqRow_17++;
										} else {
											nb_duplicates_tUniqRow_17++;
										}

										tos_count_tUniqRow_17++;

										/**
										 * [tUniqRow_17 main ] stop
										 */

										/**
										 * [tUniqRow_17 process_data_begin ] start
										 */

										currentComponent = "tUniqRow_17";

										/**
										 * [tUniqRow_17 process_data_begin ] stop
										 */
// Start of branch "row71"
										if (row71 != null) {

											/**
											 * [tDBOutput_18 main ] start
											 */

											currentComponent = "tDBOutput_18";

											if (execStat) {
												runStat.updateStatOnConnection(iterateId, 1, 1

														, "row71"

												);
											}

											whetherReject_tDBOutput_18 = false;
											if (row71.Extension__Opportunite == null) {
												pstmt_tDBOutput_18.setNull(1, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_18.setString(1, row71.Extension__Opportunite);
											}

											pstmt_tDBOutput_18.addBatch();
											nb_line_tDBOutput_18++;

											batchSizeCounter_tDBOutput_18++;

											////////// batch execute by batch size///////
											class LimitBytesHelper_tDBOutput_18 {
												public int limitBytePart1(int counter,
														java.sql.PreparedStatement pstmt_tDBOutput_18)
														throws Exception {
													try {

														for (int countEach_tDBOutput_18 : pstmt_tDBOutput_18
																.executeBatch()) {
															if (countEach_tDBOutput_18 == -2
																	|| countEach_tDBOutput_18 == -3) {
																break;
															}
															counter += countEach_tDBOutput_18;
														}

													} catch (java.sql.BatchUpdateException e) {
														globalMap.put("tDBOutput_18_ERROR_MESSAGE", e.getMessage());

														int countSum_tDBOutput_18 = 0;
														for (int countEach_tDBOutput_18 : e.getUpdateCounts()) {
															counter += (countEach_tDBOutput_18 < 0 ? 0
																	: countEach_tDBOutput_18);
														}

														System.err.println(e.getMessage());

													}
													return counter;
												}

												public int limitBytePart2(int counter,
														java.sql.PreparedStatement pstmt_tDBOutput_18)
														throws Exception {
													try {

														for (int countEach_tDBOutput_18 : pstmt_tDBOutput_18
																.executeBatch()) {
															if (countEach_tDBOutput_18 == -2
																	|| countEach_tDBOutput_18 == -3) {
																break;
															}
															counter += countEach_tDBOutput_18;
														}

													} catch (java.sql.BatchUpdateException e) {
														globalMap.put("tDBOutput_18_ERROR_MESSAGE", e.getMessage());

														for (int countEach_tDBOutput_18 : e.getUpdateCounts()) {
															counter += (countEach_tDBOutput_18 < 0 ? 0
																	: countEach_tDBOutput_18);
														}

														System.err.println(e.getMessage());

													}
													return counter;
												}
											}
											if ((batchSize_tDBOutput_18 > 0)
													&& (batchSize_tDBOutput_18 <= batchSizeCounter_tDBOutput_18)) {

												insertedCount_tDBOutput_18 = new LimitBytesHelper_tDBOutput_18()
														.limitBytePart1(insertedCount_tDBOutput_18, pstmt_tDBOutput_18);
												rowsToCommitCount_tDBOutput_18 = insertedCount_tDBOutput_18;

												batchSizeCounter_tDBOutput_18 = 0;
											}

											//////////// commit every////////////

											commitCounter_tDBOutput_18++;
											if (commitEvery_tDBOutput_18 <= commitCounter_tDBOutput_18) {
												if ((batchSize_tDBOutput_18 > 0)
														&& (batchSizeCounter_tDBOutput_18 > 0)) {

													insertedCount_tDBOutput_18 = new LimitBytesHelper_tDBOutput_18()
															.limitBytePart1(insertedCount_tDBOutput_18,
																	pstmt_tDBOutput_18);

													batchSizeCounter_tDBOutput_18 = 0;
												}
												if (rowsToCommitCount_tDBOutput_18 != 0) {

												}
												conn_tDBOutput_18.commit();
												if (rowsToCommitCount_tDBOutput_18 != 0) {

													rowsToCommitCount_tDBOutput_18 = 0;
												}
												commitCounter_tDBOutput_18 = 0;
											}

											tos_count_tDBOutput_18++;

											/**
											 * [tDBOutput_18 main ] stop
											 */

											/**
											 * [tDBOutput_18 process_data_begin ] start
											 */

											currentComponent = "tDBOutput_18";

											/**
											 * [tDBOutput_18 process_data_begin ] stop
											 */

											/**
											 * [tDBOutput_18 process_data_end ] start
											 */

											currentComponent = "tDBOutput_18";

											/**
											 * [tDBOutput_18 process_data_end ] stop
											 */

										} // End of branch "row71"

										/**
										 * [tUniqRow_17 process_data_end ] start
										 */

										currentComponent = "tUniqRow_17";

										/**
										 * [tUniqRow_17 process_data_end ] stop
										 */

									} // End of branch "q"

									/**
									 * [tMap_53 process_data_end ] start
									 */

									currentComponent = "tMap_53";

									/**
									 * [tMap_53 process_data_end ] stop
									 */

								} // End of branch "Extension_id"

// Start of branch "Dim_nature_offre_mobile"
								if (Dim_nature_offre_mobile != null) {

									/**
									 * [tMap_50 main ] start
									 */

									currentComponent = "tMap_50";

									if (execStat) {
										runStat.updateStatOnConnection(iterateId, 1, 1

												, "Dim_nature_offre_mobile"

										);
									}

									boolean hasCasePrimitiveKeyWithNull_tMap_50 = false;

									// ###############################
									// # Input tables (lookups)
									boolean rejectedInnerJoin_tMap_50 = false;
									boolean mainRowRejected_tMap_50 = false;

									///////////////////////////////////////////////
									// Starting Lookup Table "row65"
									///////////////////////////////////////////////

									boolean forceLooprow65 = false;

									row65Struct row65ObjectFromLookup = null;

									if (!rejectedInnerJoin_tMap_50) { // G_TM_M_020

										hasCasePrimitiveKeyWithNull_tMap_50 = false;

										row65HashKey.Nature_Offre = Dim_nature_offre_mobile.Nature_Offre;

										row65HashKey.hashCodeDirty = true;

										tHash_Lookup_row65.lookup(row65HashKey);

										if (!tHash_Lookup_row65.hasNext()) { // G_TM_M_090

											rejectedInnerJoin_tMap_50 = true;

										} // G_TM_M_090

									} // G_TM_M_020

									if (tHash_Lookup_row65 != null && tHash_Lookup_row65.getCount(row65HashKey) > 1) { // G
																														// 071

										// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup
										// 'row65' and it contains more one result from keys : row65.Nature_Offre = '" +
										// row65HashKey.Nature_Offre + "'");
									} // G 071

									row65Struct row65 = null;

									row65Struct fromLookup_row65 = null;
									row65 = row65Default;

									if (tHash_Lookup_row65 != null && tHash_Lookup_row65.hasNext()) { // G 099

										fromLookup_row65 = tHash_Lookup_row65.next();

									} // G 099

									if (fromLookup_row65 != null) {
										row65 = fromLookup_row65;
									}

									// ###############################
									{ // start of Var scope

										// ###############################
										// # Vars tables

										Var__tMap_50__Struct Var = Var__tMap_50;// ###############################
										// ###############################
										// # Output tables

										v = null;

										if (!rejectedInnerJoin_tMap_50) {
										} // closing inner join bracket (1)
// ###### START REJECTS ##### 

// # Output reject table : 'v'
// # Filter conditions 
										if (rejectedInnerJoin_tMap_50) {
											v_tmp.Nature_Offre = Dim_nature_offre_mobile.Nature_Offre;
											v = v_tmp;
										} // closing filter/reject
// ###############################

									} // end of Var scope

									rejectedInnerJoin_tMap_50 = false;

									tos_count_tMap_50++;

									/**
									 * [tMap_50 main ] stop
									 */

									/**
									 * [tMap_50 process_data_begin ] start
									 */

									currentComponent = "tMap_50";

									/**
									 * [tMap_50 process_data_begin ] stop
									 */
// Start of branch "v"
									if (v != null) {

										/**
										 * [tUniqRow_14 main ] start
										 */

										currentComponent = "tUniqRow_14";

										if (execStat) {
											runStat.updateStatOnConnection(iterateId, 1, 1

													, "v"

											);
										}

										row66 = null;
										finder_tUniqRow_14.Nature_Offre = v.Nature_Offre;
										finder_tUniqRow_14.hashCodeDirty = true;
										if (!keystUniqRow_14.contains(finder_tUniqRow_14)) {
											KeyStruct_tUniqRow_14 new_tUniqRow_14 = new KeyStruct_tUniqRow_14();

											new_tUniqRow_14.Nature_Offre = v.Nature_Offre;

											keystUniqRow_14.add(new_tUniqRow_14);
											if (row66 == null) {

												row66 = new row66Struct();
											}
											row66.Nature_Offre = v.Nature_Offre;
											nb_uniques_tUniqRow_14++;
										} else {
											nb_duplicates_tUniqRow_14++;
										}

										tos_count_tUniqRow_14++;

										/**
										 * [tUniqRow_14 main ] stop
										 */

										/**
										 * [tUniqRow_14 process_data_begin ] start
										 */

										currentComponent = "tUniqRow_14";

										/**
										 * [tUniqRow_14 process_data_begin ] stop
										 */
// Start of branch "row66"
										if (row66 != null) {

											/**
											 * [tDBOutput_15 main ] start
											 */

											currentComponent = "tDBOutput_15";

											if (execStat) {
												runStat.updateStatOnConnection(iterateId, 1, 1

														, "row66"

												);
											}

											whetherReject_tDBOutput_15 = false;
											if (row66.Nature_Offre == null) {
												pstmt_tDBOutput_15.setNull(1, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_15.setString(1, row66.Nature_Offre);
											}

											pstmt_tDBOutput_15.addBatch();
											nb_line_tDBOutput_15++;

											batchSizeCounter_tDBOutput_15++;

											////////// batch execute by batch size///////
											class LimitBytesHelper_tDBOutput_15 {
												public int limitBytePart1(int counter,
														java.sql.PreparedStatement pstmt_tDBOutput_15)
														throws Exception {
													try {

														for (int countEach_tDBOutput_15 : pstmt_tDBOutput_15
																.executeBatch()) {
															if (countEach_tDBOutput_15 == -2
																	|| countEach_tDBOutput_15 == -3) {
																break;
															}
															counter += countEach_tDBOutput_15;
														}

													} catch (java.sql.BatchUpdateException e) {
														globalMap.put("tDBOutput_15_ERROR_MESSAGE", e.getMessage());

														int countSum_tDBOutput_15 = 0;
														for (int countEach_tDBOutput_15 : e.getUpdateCounts()) {
															counter += (countEach_tDBOutput_15 < 0 ? 0
																	: countEach_tDBOutput_15);
														}

														System.err.println(e.getMessage());

													}
													return counter;
												}

												public int limitBytePart2(int counter,
														java.sql.PreparedStatement pstmt_tDBOutput_15)
														throws Exception {
													try {

														for (int countEach_tDBOutput_15 : pstmt_tDBOutput_15
																.executeBatch()) {
															if (countEach_tDBOutput_15 == -2
																	|| countEach_tDBOutput_15 == -3) {
																break;
															}
															counter += countEach_tDBOutput_15;
														}

													} catch (java.sql.BatchUpdateException e) {
														globalMap.put("tDBOutput_15_ERROR_MESSAGE", e.getMessage());

														for (int countEach_tDBOutput_15 : e.getUpdateCounts()) {
															counter += (countEach_tDBOutput_15 < 0 ? 0
																	: countEach_tDBOutput_15);
														}

														System.err.println(e.getMessage());

													}
													return counter;
												}
											}
											if ((batchSize_tDBOutput_15 > 0)
													&& (batchSize_tDBOutput_15 <= batchSizeCounter_tDBOutput_15)) {

												insertedCount_tDBOutput_15 = new LimitBytesHelper_tDBOutput_15()
														.limitBytePart1(insertedCount_tDBOutput_15, pstmt_tDBOutput_15);
												rowsToCommitCount_tDBOutput_15 = insertedCount_tDBOutput_15;

												batchSizeCounter_tDBOutput_15 = 0;
											}

											//////////// commit every////////////

											commitCounter_tDBOutput_15++;
											if (commitEvery_tDBOutput_15 <= commitCounter_tDBOutput_15) {
												if ((batchSize_tDBOutput_15 > 0)
														&& (batchSizeCounter_tDBOutput_15 > 0)) {

													insertedCount_tDBOutput_15 = new LimitBytesHelper_tDBOutput_15()
															.limitBytePart1(insertedCount_tDBOutput_15,
																	pstmt_tDBOutput_15);

													batchSizeCounter_tDBOutput_15 = 0;
												}
												if (rowsToCommitCount_tDBOutput_15 != 0) {

												}
												conn_tDBOutput_15.commit();
												if (rowsToCommitCount_tDBOutput_15 != 0) {

													rowsToCommitCount_tDBOutput_15 = 0;
												}
												commitCounter_tDBOutput_15 = 0;
											}

											tos_count_tDBOutput_15++;

											/**
											 * [tDBOutput_15 main ] stop
											 */

											/**
											 * [tDBOutput_15 process_data_begin ] start
											 */

											currentComponent = "tDBOutput_15";

											/**
											 * [tDBOutput_15 process_data_begin ] stop
											 */

											/**
											 * [tDBOutput_15 process_data_end ] start
											 */

											currentComponent = "tDBOutput_15";

											/**
											 * [tDBOutput_15 process_data_end ] stop
											 */

										} // End of branch "row66"

										/**
										 * [tUniqRow_14 process_data_end ] start
										 */

										currentComponent = "tUniqRow_14";

										/**
										 * [tUniqRow_14 process_data_end ] stop
										 */

									} // End of branch "v"

									/**
									 * [tMap_50 process_data_end ] start
									 */

									currentComponent = "tMap_50";

									/**
									 * [tMap_50 process_data_end ] stop
									 */

								} // End of branch "Dim_nature_offre_mobile"

// Start of branch "Dim_offre_mobile"
								if (Dim_offre_mobile != null) {

									/**
									 * [tMap_49 main ] start
									 */

									currentComponent = "tMap_49";

									if (execStat) {
										runStat.updateStatOnConnection(iterateId, 1, 1

												, "Dim_offre_mobile"

										);
									}

									boolean hasCasePrimitiveKeyWithNull_tMap_49 = false;

									// ###############################
									// # Input tables (lookups)
									boolean rejectedInnerJoin_tMap_49 = false;
									boolean mainRowRejected_tMap_49 = false;

									///////////////////////////////////////////////
									// Starting Lookup Table "row62"
									///////////////////////////////////////////////

									boolean forceLooprow62 = false;

									row62Struct row62ObjectFromLookup = null;

									if (!rejectedInnerJoin_tMap_49) { // G_TM_M_020

										hasCasePrimitiveKeyWithNull_tMap_49 = false;

										row62HashKey.Offre = Dim_offre_mobile.Offre;

										row62HashKey.hashCodeDirty = true;

										tHash_Lookup_row62.lookup(row62HashKey);

										if (!tHash_Lookup_row62.hasNext()) { // G_TM_M_090

											rejectedInnerJoin_tMap_49 = true;

										} // G_TM_M_090

									} // G_TM_M_020

									if (tHash_Lookup_row62 != null && tHash_Lookup_row62.getCount(row62HashKey) > 1) { // G
																														// 071

										// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup
										// 'row62' and it contains more one result from keys : row62.Offre = '" +
										// row62HashKey.Offre + "'");
									} // G 071

									row62Struct row62 = null;

									row62Struct fromLookup_row62 = null;
									row62 = row62Default;

									if (tHash_Lookup_row62 != null && tHash_Lookup_row62.hasNext()) { // G 099

										fromLookup_row62 = tHash_Lookup_row62.next();

									} // G 099

									if (fromLookup_row62 != null) {
										row62 = fromLookup_row62;
									}

									// ###############################
									{ // start of Var scope

										// ###############################
										// # Vars tables

										Var__tMap_49__Struct Var = Var__tMap_49;// ###############################
										// ###############################
										// # Output tables

										c = null;

										if (!rejectedInnerJoin_tMap_49) {
										} // closing inner join bracket (1)
// ###### START REJECTS ##### 

// # Output reject table : 'c'
// # Filter conditions 
										if (rejectedInnerJoin_tMap_49) {
											c_tmp.Offre = Dim_offre_mobile.Offre;
											c = c_tmp;
										} // closing filter/reject
// ###############################

									} // end of Var scope

									rejectedInnerJoin_tMap_49 = false;

									tos_count_tMap_49++;

									/**
									 * [tMap_49 main ] stop
									 */

									/**
									 * [tMap_49 process_data_begin ] start
									 */

									currentComponent = "tMap_49";

									/**
									 * [tMap_49 process_data_begin ] stop
									 */
// Start of branch "c"
									if (c != null) {

										/**
										 * [tUniqRow_13 main ] start
										 */

										currentComponent = "tUniqRow_13";

										if (execStat) {
											runStat.updateStatOnConnection(iterateId, 1, 1

													, "c"

											);
										}

										row64 = null;
										finder_tUniqRow_13.Offre = c.Offre;
										finder_tUniqRow_13.hashCodeDirty = true;
										if (!keystUniqRow_13.contains(finder_tUniqRow_13)) {
											KeyStruct_tUniqRow_13 new_tUniqRow_13 = new KeyStruct_tUniqRow_13();

											new_tUniqRow_13.Offre = c.Offre;

											keystUniqRow_13.add(new_tUniqRow_13);
											if (row64 == null) {

												row64 = new row64Struct();
											}
											row64.Offre = c.Offre;
											nb_uniques_tUniqRow_13++;
										} else {
											nb_duplicates_tUniqRow_13++;
										}

										tos_count_tUniqRow_13++;

										/**
										 * [tUniqRow_13 main ] stop
										 */

										/**
										 * [tUniqRow_13 process_data_begin ] start
										 */

										currentComponent = "tUniqRow_13";

										/**
										 * [tUniqRow_13 process_data_begin ] stop
										 */
// Start of branch "row64"
										if (row64 != null) {

											/**
											 * [tDBOutput_14 main ] start
											 */

											currentComponent = "tDBOutput_14";

											if (execStat) {
												runStat.updateStatOnConnection(iterateId, 1, 1

														, "row64"

												);
											}

											whetherReject_tDBOutput_14 = false;
											if (row64.Offre == null) {
												pstmt_tDBOutput_14.setNull(1, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_14.setString(1, row64.Offre);
											}

											pstmt_tDBOutput_14.addBatch();
											nb_line_tDBOutput_14++;

											batchSizeCounter_tDBOutput_14++;

											////////// batch execute by batch size///////
											class LimitBytesHelper_tDBOutput_14 {
												public int limitBytePart1(int counter,
														java.sql.PreparedStatement pstmt_tDBOutput_14)
														throws Exception {
													try {

														for (int countEach_tDBOutput_14 : pstmt_tDBOutput_14
																.executeBatch()) {
															if (countEach_tDBOutput_14 == -2
																	|| countEach_tDBOutput_14 == -3) {
																break;
															}
															counter += countEach_tDBOutput_14;
														}

													} catch (java.sql.BatchUpdateException e) {
														globalMap.put("tDBOutput_14_ERROR_MESSAGE", e.getMessage());

														int countSum_tDBOutput_14 = 0;
														for (int countEach_tDBOutput_14 : e.getUpdateCounts()) {
															counter += (countEach_tDBOutput_14 < 0 ? 0
																	: countEach_tDBOutput_14);
														}

														System.err.println(e.getMessage());

													}
													return counter;
												}

												public int limitBytePart2(int counter,
														java.sql.PreparedStatement pstmt_tDBOutput_14)
														throws Exception {
													try {

														for (int countEach_tDBOutput_14 : pstmt_tDBOutput_14
																.executeBatch()) {
															if (countEach_tDBOutput_14 == -2
																	|| countEach_tDBOutput_14 == -3) {
																break;
															}
															counter += countEach_tDBOutput_14;
														}

													} catch (java.sql.BatchUpdateException e) {
														globalMap.put("tDBOutput_14_ERROR_MESSAGE", e.getMessage());

														for (int countEach_tDBOutput_14 : e.getUpdateCounts()) {
															counter += (countEach_tDBOutput_14 < 0 ? 0
																	: countEach_tDBOutput_14);
														}

														System.err.println(e.getMessage());

													}
													return counter;
												}
											}
											if ((batchSize_tDBOutput_14 > 0)
													&& (batchSize_tDBOutput_14 <= batchSizeCounter_tDBOutput_14)) {

												insertedCount_tDBOutput_14 = new LimitBytesHelper_tDBOutput_14()
														.limitBytePart1(insertedCount_tDBOutput_14, pstmt_tDBOutput_14);
												rowsToCommitCount_tDBOutput_14 = insertedCount_tDBOutput_14;

												batchSizeCounter_tDBOutput_14 = 0;
											}

											//////////// commit every////////////

											commitCounter_tDBOutput_14++;
											if (commitEvery_tDBOutput_14 <= commitCounter_tDBOutput_14) {
												if ((batchSize_tDBOutput_14 > 0)
														&& (batchSizeCounter_tDBOutput_14 > 0)) {

													insertedCount_tDBOutput_14 = new LimitBytesHelper_tDBOutput_14()
															.limitBytePart1(insertedCount_tDBOutput_14,
																	pstmt_tDBOutput_14);

													batchSizeCounter_tDBOutput_14 = 0;
												}
												if (rowsToCommitCount_tDBOutput_14 != 0) {

												}
												conn_tDBOutput_14.commit();
												if (rowsToCommitCount_tDBOutput_14 != 0) {

													rowsToCommitCount_tDBOutput_14 = 0;
												}
												commitCounter_tDBOutput_14 = 0;
											}

											tos_count_tDBOutput_14++;

											/**
											 * [tDBOutput_14 main ] stop
											 */

											/**
											 * [tDBOutput_14 process_data_begin ] start
											 */

											currentComponent = "tDBOutput_14";

											/**
											 * [tDBOutput_14 process_data_begin ] stop
											 */

											/**
											 * [tDBOutput_14 process_data_end ] start
											 */

											currentComponent = "tDBOutput_14";

											/**
											 * [tDBOutput_14 process_data_end ] stop
											 */

										} // End of branch "row64"

										/**
										 * [tUniqRow_13 process_data_end ] start
										 */

										currentComponent = "tUniqRow_13";

										/**
										 * [tUniqRow_13 process_data_end ] stop
										 */

									} // End of branch "c"

									/**
									 * [tMap_49 process_data_end ] start
									 */

									currentComponent = "tMap_49";

									/**
									 * [tMap_49 process_data_end ] stop
									 */

								} // End of branch "Dim_offre_mobile"

								/**
								 * [tMap_48 process_data_end ] start
								 */

								currentComponent = "tMap_48";

								/**
								 * [tMap_48 process_data_end ] stop
								 */

							} // End of branch "row58"

							/**
							 * [tFileInputExcel_35 process_data_end ] start
							 */

							currentComponent = "tFileInputExcel_35";

							/**
							 * [tFileInputExcel_35 process_data_end ] stop
							 */

							/**
							 * [tFileInputExcel_35 end ] start
							 */

							currentComponent = "tFileInputExcel_35";

						}

						globalMap.put("tFileInputExcel_35_NB_LINE", nb_line_tFileInputExcel_35);

					}

				} finally {

					if (!(source_tFileInputExcel_35 instanceof java.io.InputStream)) {
						workbook_tFileInputExcel_35.getPackage().revert();
					}

				}

				ok_Hash.put("tFileInputExcel_35", true);
				end_Hash.put("tFileInputExcel_35", System.currentTimeMillis());

				/**
				 * [tFileInputExcel_35 end ] stop
				 */

				/**
				 * [tMap_48 end ] start
				 */

				currentComponent = "tMap_48";

// ###############################
// # Lookup hashes releasing
// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row58");
				}

				ok_Hash.put("tMap_48", true);
				end_Hash.put("tMap_48", System.currentTimeMillis());

				/**
				 * [tMap_48 end ] stop
				 */

				/**
				 * [tMap_51 end ] start
				 */

				currentComponent = "tMap_51";

// ###############################
// # Lookup hashes releasing
				if (tHash_Lookup_row67 != null) {
					tHash_Lookup_row67.endGet();
				}
				globalMap.remove("tHash_Lookup_row67");

// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "Dim_Reference_OSM_Opportunite_mobile");
				}

				ok_Hash.put("tMap_51", true);
				end_Hash.put("tMap_51", System.currentTimeMillis());

				/**
				 * [tMap_51 end ] stop
				 */

				/**
				 * [tUniqRow_15 end ] start
				 */

				currentComponent = "tUniqRow_15";

				globalMap.put("tUniqRow_15_NB_UNIQUES", nb_uniques_tUniqRow_15);
				globalMap.put("tUniqRow_15_NB_DUPLICATES", nb_duplicates_tUniqRow_15);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "b");
				}

				ok_Hash.put("tUniqRow_15", true);
				end_Hash.put("tUniqRow_15", System.currentTimeMillis());

				/**
				 * [tUniqRow_15 end ] stop
				 */

				/**
				 * [tDBOutput_16 end ] start
				 */

				currentComponent = "tDBOutput_16";

				try {
					int countSum_tDBOutput_16 = 0;
					if (pstmt_tDBOutput_16 != null && batchSizeCounter_tDBOutput_16 > 0) {

						for (int countEach_tDBOutput_16 : pstmt_tDBOutput_16.executeBatch()) {
							if (countEach_tDBOutput_16 == -2 || countEach_tDBOutput_16 == -3) {
								break;
							}
							countSum_tDBOutput_16 += countEach_tDBOutput_16;
						}
						rowsToCommitCount_tDBOutput_16 += countSum_tDBOutput_16;

					}

					insertedCount_tDBOutput_16 += countSum_tDBOutput_16;

				} catch (java.sql.BatchUpdateException e) {
					globalMap.put("tDBOutput_16_ERROR_MESSAGE", e.getMessage());

					int countSum_tDBOutput_16 = 0;
					for (int countEach_tDBOutput_16 : e.getUpdateCounts()) {
						countSum_tDBOutput_16 += (countEach_tDBOutput_16 < 0 ? 0 : countEach_tDBOutput_16);
					}
					rowsToCommitCount_tDBOutput_16 += countSum_tDBOutput_16;

					insertedCount_tDBOutput_16 += countSum_tDBOutput_16;

					System.err.println(e.getMessage());

				}
				if (pstmt_tDBOutput_16 != null) {

					pstmt_tDBOutput_16.close();
					resourceMap.remove("pstmt_tDBOutput_16");

				}
				resourceMap.put("statementClosed_tDBOutput_16", true);
				if (rowsToCommitCount_tDBOutput_16 != 0) {

				}
				conn_tDBOutput_16.commit();
				if (rowsToCommitCount_tDBOutput_16 != 0) {

					rowsToCommitCount_tDBOutput_16 = 0;
				}
				commitCounter_tDBOutput_16 = 0;
				conn_tDBOutput_16.close();
				resourceMap.put("finish_tDBOutput_16", true);

				nb_line_deleted_tDBOutput_16 = nb_line_deleted_tDBOutput_16 + deletedCount_tDBOutput_16;
				nb_line_update_tDBOutput_16 = nb_line_update_tDBOutput_16 + updatedCount_tDBOutput_16;
				nb_line_inserted_tDBOutput_16 = nb_line_inserted_tDBOutput_16 + insertedCount_tDBOutput_16;
				nb_line_rejected_tDBOutput_16 = nb_line_rejected_tDBOutput_16 + rejectedCount_tDBOutput_16;

				globalMap.put("tDBOutput_16_NB_LINE", nb_line_tDBOutput_16);
				globalMap.put("tDBOutput_16_NB_LINE_UPDATED", nb_line_update_tDBOutput_16);
				globalMap.put("tDBOutput_16_NB_LINE_INSERTED", nb_line_inserted_tDBOutput_16);
				globalMap.put("tDBOutput_16_NB_LINE_DELETED", nb_line_deleted_tDBOutput_16);
				globalMap.put("tDBOutput_16_NB_LINE_REJECTED", nb_line_rejected_tDBOutput_16);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row68");
				}

				ok_Hash.put("tDBOutput_16", true);
				end_Hash.put("tDBOutput_16", System.currentTimeMillis());

				/**
				 * [tDBOutput_16 end ] stop
				 */

				/**
				 * [tMap_52 end ] start
				 */

				currentComponent = "tMap_52";

// ###############################
// # Lookup hashes releasing
				if (tHash_Lookup_row69 != null) {
					tHash_Lookup_row69.endGet();
				}
				globalMap.remove("tHash_Lookup_row69");

// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "Dim_type_engagement_mobile");
				}

				ok_Hash.put("tMap_52", true);
				end_Hash.put("tMap_52", System.currentTimeMillis());

				/**
				 * [tMap_52 end ] stop
				 */

				/**
				 * [tUniqRow_16 end ] start
				 */

				currentComponent = "tUniqRow_16";

				globalMap.put("tUniqRow_16_NB_UNIQUES", nb_uniques_tUniqRow_16);
				globalMap.put("tUniqRow_16_NB_DUPLICATES", nb_duplicates_tUniqRow_16);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "n");
				}

				ok_Hash.put("tUniqRow_16", true);
				end_Hash.put("tUniqRow_16", System.currentTimeMillis());

				/**
				 * [tUniqRow_16 end ] stop
				 */

				/**
				 * [tDBOutput_17 end ] start
				 */

				currentComponent = "tDBOutput_17";

				try {
					int countSum_tDBOutput_17 = 0;
					if (pstmt_tDBOutput_17 != null && batchSizeCounter_tDBOutput_17 > 0) {

						for (int countEach_tDBOutput_17 : pstmt_tDBOutput_17.executeBatch()) {
							if (countEach_tDBOutput_17 == -2 || countEach_tDBOutput_17 == -3) {
								break;
							}
							countSum_tDBOutput_17 += countEach_tDBOutput_17;
						}
						rowsToCommitCount_tDBOutput_17 += countSum_tDBOutput_17;

					}

					insertedCount_tDBOutput_17 += countSum_tDBOutput_17;

				} catch (java.sql.BatchUpdateException e) {
					globalMap.put("tDBOutput_17_ERROR_MESSAGE", e.getMessage());

					int countSum_tDBOutput_17 = 0;
					for (int countEach_tDBOutput_17 : e.getUpdateCounts()) {
						countSum_tDBOutput_17 += (countEach_tDBOutput_17 < 0 ? 0 : countEach_tDBOutput_17);
					}
					rowsToCommitCount_tDBOutput_17 += countSum_tDBOutput_17;

					insertedCount_tDBOutput_17 += countSum_tDBOutput_17;

					System.err.println(e.getMessage());

				}
				if (pstmt_tDBOutput_17 != null) {

					pstmt_tDBOutput_17.close();
					resourceMap.remove("pstmt_tDBOutput_17");

				}
				resourceMap.put("statementClosed_tDBOutput_17", true);
				if (rowsToCommitCount_tDBOutput_17 != 0) {

				}
				conn_tDBOutput_17.commit();
				if (rowsToCommitCount_tDBOutput_17 != 0) {

					rowsToCommitCount_tDBOutput_17 = 0;
				}
				commitCounter_tDBOutput_17 = 0;
				conn_tDBOutput_17.close();
				resourceMap.put("finish_tDBOutput_17", true);

				nb_line_deleted_tDBOutput_17 = nb_line_deleted_tDBOutput_17 + deletedCount_tDBOutput_17;
				nb_line_update_tDBOutput_17 = nb_line_update_tDBOutput_17 + updatedCount_tDBOutput_17;
				nb_line_inserted_tDBOutput_17 = nb_line_inserted_tDBOutput_17 + insertedCount_tDBOutput_17;
				nb_line_rejected_tDBOutput_17 = nb_line_rejected_tDBOutput_17 + rejectedCount_tDBOutput_17;

				globalMap.put("tDBOutput_17_NB_LINE", nb_line_tDBOutput_17);
				globalMap.put("tDBOutput_17_NB_LINE_UPDATED", nb_line_update_tDBOutput_17);
				globalMap.put("tDBOutput_17_NB_LINE_INSERTED", nb_line_inserted_tDBOutput_17);
				globalMap.put("tDBOutput_17_NB_LINE_DELETED", nb_line_deleted_tDBOutput_17);
				globalMap.put("tDBOutput_17_NB_LINE_REJECTED", nb_line_rejected_tDBOutput_17);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row70");
				}

				ok_Hash.put("tDBOutput_17", true);
				end_Hash.put("tDBOutput_17", System.currentTimeMillis());

				/**
				 * [tDBOutput_17 end ] stop
				 */

				/**
				 * [tMap_53 end ] start
				 */

				currentComponent = "tMap_53";

// ###############################
// # Lookup hashes releasing
				if (tHash_Lookup_row72 != null) {
					tHash_Lookup_row72.endGet();
				}
				globalMap.remove("tHash_Lookup_row72");

// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "Extension_id");
				}

				ok_Hash.put("tMap_53", true);
				end_Hash.put("tMap_53", System.currentTimeMillis());

				/**
				 * [tMap_53 end ] stop
				 */

				/**
				 * [tUniqRow_17 end ] start
				 */

				currentComponent = "tUniqRow_17";

				globalMap.put("tUniqRow_17_NB_UNIQUES", nb_uniques_tUniqRow_17);
				globalMap.put("tUniqRow_17_NB_DUPLICATES", nb_duplicates_tUniqRow_17);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "q");
				}

				ok_Hash.put("tUniqRow_17", true);
				end_Hash.put("tUniqRow_17", System.currentTimeMillis());

				/**
				 * [tUniqRow_17 end ] stop
				 */

				/**
				 * [tDBOutput_18 end ] start
				 */

				currentComponent = "tDBOutput_18";

				try {
					int countSum_tDBOutput_18 = 0;
					if (pstmt_tDBOutput_18 != null && batchSizeCounter_tDBOutput_18 > 0) {

						for (int countEach_tDBOutput_18 : pstmt_tDBOutput_18.executeBatch()) {
							if (countEach_tDBOutput_18 == -2 || countEach_tDBOutput_18 == -3) {
								break;
							}
							countSum_tDBOutput_18 += countEach_tDBOutput_18;
						}
						rowsToCommitCount_tDBOutput_18 += countSum_tDBOutput_18;

					}

					insertedCount_tDBOutput_18 += countSum_tDBOutput_18;

				} catch (java.sql.BatchUpdateException e) {
					globalMap.put("tDBOutput_18_ERROR_MESSAGE", e.getMessage());

					int countSum_tDBOutput_18 = 0;
					for (int countEach_tDBOutput_18 : e.getUpdateCounts()) {
						countSum_tDBOutput_18 += (countEach_tDBOutput_18 < 0 ? 0 : countEach_tDBOutput_18);
					}
					rowsToCommitCount_tDBOutput_18 += countSum_tDBOutput_18;

					insertedCount_tDBOutput_18 += countSum_tDBOutput_18;

					System.err.println(e.getMessage());

				}
				if (pstmt_tDBOutput_18 != null) {

					pstmt_tDBOutput_18.close();
					resourceMap.remove("pstmt_tDBOutput_18");

				}
				resourceMap.put("statementClosed_tDBOutput_18", true);
				if (rowsToCommitCount_tDBOutput_18 != 0) {

				}
				conn_tDBOutput_18.commit();
				if (rowsToCommitCount_tDBOutput_18 != 0) {

					rowsToCommitCount_tDBOutput_18 = 0;
				}
				commitCounter_tDBOutput_18 = 0;
				conn_tDBOutput_18.close();
				resourceMap.put("finish_tDBOutput_18", true);

				nb_line_deleted_tDBOutput_18 = nb_line_deleted_tDBOutput_18 + deletedCount_tDBOutput_18;
				nb_line_update_tDBOutput_18 = nb_line_update_tDBOutput_18 + updatedCount_tDBOutput_18;
				nb_line_inserted_tDBOutput_18 = nb_line_inserted_tDBOutput_18 + insertedCount_tDBOutput_18;
				nb_line_rejected_tDBOutput_18 = nb_line_rejected_tDBOutput_18 + rejectedCount_tDBOutput_18;

				globalMap.put("tDBOutput_18_NB_LINE", nb_line_tDBOutput_18);
				globalMap.put("tDBOutput_18_NB_LINE_UPDATED", nb_line_update_tDBOutput_18);
				globalMap.put("tDBOutput_18_NB_LINE_INSERTED", nb_line_inserted_tDBOutput_18);
				globalMap.put("tDBOutput_18_NB_LINE_DELETED", nb_line_deleted_tDBOutput_18);
				globalMap.put("tDBOutput_18_NB_LINE_REJECTED", nb_line_rejected_tDBOutput_18);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row71");
				}

				ok_Hash.put("tDBOutput_18", true);
				end_Hash.put("tDBOutput_18", System.currentTimeMillis());

				/**
				 * [tDBOutput_18 end ] stop
				 */

				/**
				 * [tMap_50 end ] start
				 */

				currentComponent = "tMap_50";

// ###############################
// # Lookup hashes releasing
				if (tHash_Lookup_row65 != null) {
					tHash_Lookup_row65.endGet();
				}
				globalMap.remove("tHash_Lookup_row65");

// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "Dim_nature_offre_mobile");
				}

				ok_Hash.put("tMap_50", true);
				end_Hash.put("tMap_50", System.currentTimeMillis());

				/**
				 * [tMap_50 end ] stop
				 */

				/**
				 * [tUniqRow_14 end ] start
				 */

				currentComponent = "tUniqRow_14";

				globalMap.put("tUniqRow_14_NB_UNIQUES", nb_uniques_tUniqRow_14);
				globalMap.put("tUniqRow_14_NB_DUPLICATES", nb_duplicates_tUniqRow_14);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "v");
				}

				ok_Hash.put("tUniqRow_14", true);
				end_Hash.put("tUniqRow_14", System.currentTimeMillis());

				/**
				 * [tUniqRow_14 end ] stop
				 */

				/**
				 * [tDBOutput_15 end ] start
				 */

				currentComponent = "tDBOutput_15";

				try {
					int countSum_tDBOutput_15 = 0;
					if (pstmt_tDBOutput_15 != null && batchSizeCounter_tDBOutput_15 > 0) {

						for (int countEach_tDBOutput_15 : pstmt_tDBOutput_15.executeBatch()) {
							if (countEach_tDBOutput_15 == -2 || countEach_tDBOutput_15 == -3) {
								break;
							}
							countSum_tDBOutput_15 += countEach_tDBOutput_15;
						}
						rowsToCommitCount_tDBOutput_15 += countSum_tDBOutput_15;

					}

					insertedCount_tDBOutput_15 += countSum_tDBOutput_15;

				} catch (java.sql.BatchUpdateException e) {
					globalMap.put("tDBOutput_15_ERROR_MESSAGE", e.getMessage());

					int countSum_tDBOutput_15 = 0;
					for (int countEach_tDBOutput_15 : e.getUpdateCounts()) {
						countSum_tDBOutput_15 += (countEach_tDBOutput_15 < 0 ? 0 : countEach_tDBOutput_15);
					}
					rowsToCommitCount_tDBOutput_15 += countSum_tDBOutput_15;

					insertedCount_tDBOutput_15 += countSum_tDBOutput_15;

					System.err.println(e.getMessage());

				}
				if (pstmt_tDBOutput_15 != null) {

					pstmt_tDBOutput_15.close();
					resourceMap.remove("pstmt_tDBOutput_15");

				}
				resourceMap.put("statementClosed_tDBOutput_15", true);
				if (rowsToCommitCount_tDBOutput_15 != 0) {

				}
				conn_tDBOutput_15.commit();
				if (rowsToCommitCount_tDBOutput_15 != 0) {

					rowsToCommitCount_tDBOutput_15 = 0;
				}
				commitCounter_tDBOutput_15 = 0;
				conn_tDBOutput_15.close();
				resourceMap.put("finish_tDBOutput_15", true);

				nb_line_deleted_tDBOutput_15 = nb_line_deleted_tDBOutput_15 + deletedCount_tDBOutput_15;
				nb_line_update_tDBOutput_15 = nb_line_update_tDBOutput_15 + updatedCount_tDBOutput_15;
				nb_line_inserted_tDBOutput_15 = nb_line_inserted_tDBOutput_15 + insertedCount_tDBOutput_15;
				nb_line_rejected_tDBOutput_15 = nb_line_rejected_tDBOutput_15 + rejectedCount_tDBOutput_15;

				globalMap.put("tDBOutput_15_NB_LINE", nb_line_tDBOutput_15);
				globalMap.put("tDBOutput_15_NB_LINE_UPDATED", nb_line_update_tDBOutput_15);
				globalMap.put("tDBOutput_15_NB_LINE_INSERTED", nb_line_inserted_tDBOutput_15);
				globalMap.put("tDBOutput_15_NB_LINE_DELETED", nb_line_deleted_tDBOutput_15);
				globalMap.put("tDBOutput_15_NB_LINE_REJECTED", nb_line_rejected_tDBOutput_15);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row66");
				}

				ok_Hash.put("tDBOutput_15", true);
				end_Hash.put("tDBOutput_15", System.currentTimeMillis());

				/**
				 * [tDBOutput_15 end ] stop
				 */

				/**
				 * [tMap_49 end ] start
				 */

				currentComponent = "tMap_49";

// ###############################
// # Lookup hashes releasing
				if (tHash_Lookup_row62 != null) {
					tHash_Lookup_row62.endGet();
				}
				globalMap.remove("tHash_Lookup_row62");

// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "Dim_offre_mobile");
				}

				ok_Hash.put("tMap_49", true);
				end_Hash.put("tMap_49", System.currentTimeMillis());

				/**
				 * [tMap_49 end ] stop
				 */

				/**
				 * [tUniqRow_13 end ] start
				 */

				currentComponent = "tUniqRow_13";

				globalMap.put("tUniqRow_13_NB_UNIQUES", nb_uniques_tUniqRow_13);
				globalMap.put("tUniqRow_13_NB_DUPLICATES", nb_duplicates_tUniqRow_13);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "c");
				}

				ok_Hash.put("tUniqRow_13", true);
				end_Hash.put("tUniqRow_13", System.currentTimeMillis());

				/**
				 * [tUniqRow_13 end ] stop
				 */

				/**
				 * [tDBOutput_14 end ] start
				 */

				currentComponent = "tDBOutput_14";

				try {
					int countSum_tDBOutput_14 = 0;
					if (pstmt_tDBOutput_14 != null && batchSizeCounter_tDBOutput_14 > 0) {

						for (int countEach_tDBOutput_14 : pstmt_tDBOutput_14.executeBatch()) {
							if (countEach_tDBOutput_14 == -2 || countEach_tDBOutput_14 == -3) {
								break;
							}
							countSum_tDBOutput_14 += countEach_tDBOutput_14;
						}
						rowsToCommitCount_tDBOutput_14 += countSum_tDBOutput_14;

					}

					insertedCount_tDBOutput_14 += countSum_tDBOutput_14;

				} catch (java.sql.BatchUpdateException e) {
					globalMap.put("tDBOutput_14_ERROR_MESSAGE", e.getMessage());

					int countSum_tDBOutput_14 = 0;
					for (int countEach_tDBOutput_14 : e.getUpdateCounts()) {
						countSum_tDBOutput_14 += (countEach_tDBOutput_14 < 0 ? 0 : countEach_tDBOutput_14);
					}
					rowsToCommitCount_tDBOutput_14 += countSum_tDBOutput_14;

					insertedCount_tDBOutput_14 += countSum_tDBOutput_14;

					System.err.println(e.getMessage());

				}
				if (pstmt_tDBOutput_14 != null) {

					pstmt_tDBOutput_14.close();
					resourceMap.remove("pstmt_tDBOutput_14");

				}
				resourceMap.put("statementClosed_tDBOutput_14", true);
				if (rowsToCommitCount_tDBOutput_14 != 0) {

				}
				conn_tDBOutput_14.commit();
				if (rowsToCommitCount_tDBOutput_14 != 0) {

					rowsToCommitCount_tDBOutput_14 = 0;
				}
				commitCounter_tDBOutput_14 = 0;
				conn_tDBOutput_14.close();
				resourceMap.put("finish_tDBOutput_14", true);

				nb_line_deleted_tDBOutput_14 = nb_line_deleted_tDBOutput_14 + deletedCount_tDBOutput_14;
				nb_line_update_tDBOutput_14 = nb_line_update_tDBOutput_14 + updatedCount_tDBOutput_14;
				nb_line_inserted_tDBOutput_14 = nb_line_inserted_tDBOutput_14 + insertedCount_tDBOutput_14;
				nb_line_rejected_tDBOutput_14 = nb_line_rejected_tDBOutput_14 + rejectedCount_tDBOutput_14;

				globalMap.put("tDBOutput_14_NB_LINE", nb_line_tDBOutput_14);
				globalMap.put("tDBOutput_14_NB_LINE_UPDATED", nb_line_update_tDBOutput_14);
				globalMap.put("tDBOutput_14_NB_LINE_INSERTED", nb_line_inserted_tDBOutput_14);
				globalMap.put("tDBOutput_14_NB_LINE_DELETED", nb_line_deleted_tDBOutput_14);
				globalMap.put("tDBOutput_14_NB_LINE_REJECTED", nb_line_rejected_tDBOutput_14);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row64");
				}

				ok_Hash.put("tDBOutput_14", true);
				end_Hash.put("tDBOutput_14", System.currentTimeMillis());

				/**
				 * [tDBOutput_14 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			// free memory for "tMap_49"
			globalMap.remove("tHash_Lookup_row62");

			// free memory for "tMap_50"
			globalMap.remove("tHash_Lookup_row65");

			// free memory for "tMap_53"
			globalMap.remove("tHash_Lookup_row72");

			// free memory for "tMap_52"
			globalMap.remove("tHash_Lookup_row69");

			// free memory for "tMap_51"
			globalMap.remove("tHash_Lookup_row67");

			try {

				/**
				 * [tFileInputExcel_35 finally ] start
				 */

				currentComponent = "tFileInputExcel_35";

				/**
				 * [tFileInputExcel_35 finally ] stop
				 */

				/**
				 * [tMap_48 finally ] start
				 */

				currentComponent = "tMap_48";

				/**
				 * [tMap_48 finally ] stop
				 */

				/**
				 * [tMap_51 finally ] start
				 */

				currentComponent = "tMap_51";

				/**
				 * [tMap_51 finally ] stop
				 */

				/**
				 * [tUniqRow_15 finally ] start
				 */

				currentComponent = "tUniqRow_15";

				/**
				 * [tUniqRow_15 finally ] stop
				 */

				/**
				 * [tDBOutput_16 finally ] start
				 */

				currentComponent = "tDBOutput_16";

				try {
					if (resourceMap.get("statementClosed_tDBOutput_16") == null) {
						java.sql.PreparedStatement pstmtToClose_tDBOutput_16 = null;
						if ((pstmtToClose_tDBOutput_16 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmt_tDBOutput_16")) != null) {
							pstmtToClose_tDBOutput_16.close();
						}
					}
				} finally {
					if (resourceMap.get("finish_tDBOutput_16") == null) {
						java.sql.Connection ctn_tDBOutput_16 = null;
						if ((ctn_tDBOutput_16 = (java.sql.Connection) resourceMap.get("conn_tDBOutput_16")) != null) {
							try {
								ctn_tDBOutput_16.close();
							} catch (java.sql.SQLException sqlEx_tDBOutput_16) {
								String errorMessage_tDBOutput_16 = "failed to close the connection in tDBOutput_16 :"
										+ sqlEx_tDBOutput_16.getMessage();
								System.err.println(errorMessage_tDBOutput_16);
							}
						}
					}
				}

				/**
				 * [tDBOutput_16 finally ] stop
				 */

				/**
				 * [tMap_52 finally ] start
				 */

				currentComponent = "tMap_52";

				/**
				 * [tMap_52 finally ] stop
				 */

				/**
				 * [tUniqRow_16 finally ] start
				 */

				currentComponent = "tUniqRow_16";

				/**
				 * [tUniqRow_16 finally ] stop
				 */

				/**
				 * [tDBOutput_17 finally ] start
				 */

				currentComponent = "tDBOutput_17";

				try {
					if (resourceMap.get("statementClosed_tDBOutput_17") == null) {
						java.sql.PreparedStatement pstmtToClose_tDBOutput_17 = null;
						if ((pstmtToClose_tDBOutput_17 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmt_tDBOutput_17")) != null) {
							pstmtToClose_tDBOutput_17.close();
						}
					}
				} finally {
					if (resourceMap.get("finish_tDBOutput_17") == null) {
						java.sql.Connection ctn_tDBOutput_17 = null;
						if ((ctn_tDBOutput_17 = (java.sql.Connection) resourceMap.get("conn_tDBOutput_17")) != null) {
							try {
								ctn_tDBOutput_17.close();
							} catch (java.sql.SQLException sqlEx_tDBOutput_17) {
								String errorMessage_tDBOutput_17 = "failed to close the connection in tDBOutput_17 :"
										+ sqlEx_tDBOutput_17.getMessage();
								System.err.println(errorMessage_tDBOutput_17);
							}
						}
					}
				}

				/**
				 * [tDBOutput_17 finally ] stop
				 */

				/**
				 * [tMap_53 finally ] start
				 */

				currentComponent = "tMap_53";

				/**
				 * [tMap_53 finally ] stop
				 */

				/**
				 * [tUniqRow_17 finally ] start
				 */

				currentComponent = "tUniqRow_17";

				/**
				 * [tUniqRow_17 finally ] stop
				 */

				/**
				 * [tDBOutput_18 finally ] start
				 */

				currentComponent = "tDBOutput_18";

				try {
					if (resourceMap.get("statementClosed_tDBOutput_18") == null) {
						java.sql.PreparedStatement pstmtToClose_tDBOutput_18 = null;
						if ((pstmtToClose_tDBOutput_18 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmt_tDBOutput_18")) != null) {
							pstmtToClose_tDBOutput_18.close();
						}
					}
				} finally {
					if (resourceMap.get("finish_tDBOutput_18") == null) {
						java.sql.Connection ctn_tDBOutput_18 = null;
						if ((ctn_tDBOutput_18 = (java.sql.Connection) resourceMap.get("conn_tDBOutput_18")) != null) {
							try {
								ctn_tDBOutput_18.close();
							} catch (java.sql.SQLException sqlEx_tDBOutput_18) {
								String errorMessage_tDBOutput_18 = "failed to close the connection in tDBOutput_18 :"
										+ sqlEx_tDBOutput_18.getMessage();
								System.err.println(errorMessage_tDBOutput_18);
							}
						}
					}
				}

				/**
				 * [tDBOutput_18 finally ] stop
				 */

				/**
				 * [tMap_50 finally ] start
				 */

				currentComponent = "tMap_50";

				/**
				 * [tMap_50 finally ] stop
				 */

				/**
				 * [tUniqRow_14 finally ] start
				 */

				currentComponent = "tUniqRow_14";

				/**
				 * [tUniqRow_14 finally ] stop
				 */

				/**
				 * [tDBOutput_15 finally ] start
				 */

				currentComponent = "tDBOutput_15";

				try {
					if (resourceMap.get("statementClosed_tDBOutput_15") == null) {
						java.sql.PreparedStatement pstmtToClose_tDBOutput_15 = null;
						if ((pstmtToClose_tDBOutput_15 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmt_tDBOutput_15")) != null) {
							pstmtToClose_tDBOutput_15.close();
						}
					}
				} finally {
					if (resourceMap.get("finish_tDBOutput_15") == null) {
						java.sql.Connection ctn_tDBOutput_15 = null;
						if ((ctn_tDBOutput_15 = (java.sql.Connection) resourceMap.get("conn_tDBOutput_15")) != null) {
							try {
								ctn_tDBOutput_15.close();
							} catch (java.sql.SQLException sqlEx_tDBOutput_15) {
								String errorMessage_tDBOutput_15 = "failed to close the connection in tDBOutput_15 :"
										+ sqlEx_tDBOutput_15.getMessage();
								System.err.println(errorMessage_tDBOutput_15);
							}
						}
					}
				}

				/**
				 * [tDBOutput_15 finally ] stop
				 */

				/**
				 * [tMap_49 finally ] start
				 */

				currentComponent = "tMap_49";

				/**
				 * [tMap_49 finally ] stop
				 */

				/**
				 * [tUniqRow_13 finally ] start
				 */

				currentComponent = "tUniqRow_13";

				/**
				 * [tUniqRow_13 finally ] stop
				 */

				/**
				 * [tDBOutput_14 finally ] start
				 */

				currentComponent = "tDBOutput_14";

				try {
					if (resourceMap.get("statementClosed_tDBOutput_14") == null) {
						java.sql.PreparedStatement pstmtToClose_tDBOutput_14 = null;
						if ((pstmtToClose_tDBOutput_14 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmt_tDBOutput_14")) != null) {
							pstmtToClose_tDBOutput_14.close();
						}
					}
				} finally {
					if (resourceMap.get("finish_tDBOutput_14") == null) {
						java.sql.Connection ctn_tDBOutput_14 = null;
						if ((ctn_tDBOutput_14 = (java.sql.Connection) resourceMap.get("conn_tDBOutput_14")) != null) {
							try {
								ctn_tDBOutput_14.close();
							} catch (java.sql.SQLException sqlEx_tDBOutput_14) {
								String errorMessage_tDBOutput_14 = "failed to close the connection in tDBOutput_14 :"
										+ sqlEx_tDBOutput_14.getMessage();
								System.err.println(errorMessage_tDBOutput_14);
							}
						}
					}
				}

				/**
				 * [tDBOutput_14 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputExcel_35_SUBPROCESS_STATE", 1);
	}

	public String resuming_logs_dir_path = null;
	public String resuming_checkpoint_path = null;
	public String parent_part_launcher = null;
	private String resumeEntryMethodName = null;
	private boolean globalResumeTicket = false;

	public boolean watch = false;
	// portStats is null, it means don't execute the statistics
	public Integer portStats = null;
	public int portTraces = 4334;
	public String clientHost;
	public String defaultClientHost = "localhost";
	public String contextStr = "Default";
	public boolean isDefaultContext = true;
	public String pid = "0";
	public String rootPid = null;
	public String fatherPid = null;
	public String fatherNode = null;
	public long startTime = 0;
	public boolean isChildJob = false;
	public String log4jLevel = "";

	private boolean enableLogStash;

	private boolean execStat = true;

	private ThreadLocal<java.util.Map<String, String>> threadLocal = new ThreadLocal<java.util.Map<String, String>>() {
		protected java.util.Map<String, String> initialValue() {
			java.util.Map<String, String> threadRunResultMap = new java.util.HashMap<String, String>();
			threadRunResultMap.put("errorCode", null);
			threadRunResultMap.put("status", "");
			return threadRunResultMap;
		};
	};

	protected PropertiesWithType context_param = new PropertiesWithType();
	public java.util.Map<String, Object> parentContextMap = new java.util.HashMap<String, Object>();

	public String status = "";

	public static void main(String[] args) {
		final tzydfvq tzydfvqClass = new tzydfvq();

		int exitCode = tzydfvqClass.runJobInTOS(args);

		System.exit(exitCode);
	}

	public String[][] runJob(String[] args) {

		int exitCode = runJobInTOS(args);
		String[][] bufferValue = new String[][] { { Integer.toString(exitCode) } };

		return bufferValue;
	}

	public boolean hastBufferOutputComponent() {
		boolean hastBufferOutput = false;

		return hastBufferOutput;
	}

	public int runJobInTOS(String[] args) {
		// reset status
		status = "";

		String lastStr = "";
		for (String arg : args) {
			if (arg.equalsIgnoreCase("--context_param")) {
				lastStr = arg;
			} else if (lastStr.equals("")) {
				evalParam(arg);
			} else {
				evalParam(lastStr + " " + arg);
				lastStr = "";
			}
		}
		enableLogStash = "true".equalsIgnoreCase(System.getProperty("audit.enabled"));

		if (clientHost == null) {
			clientHost = defaultClientHost;
		}

		if (pid == null || "0".equals(pid)) {
			pid = TalendString.getAsciiRandomString(6);
		}

		if (rootPid == null) {
			rootPid = pid;
		}
		if (fatherPid == null) {
			fatherPid = pid;
		} else {
			isChildJob = true;
		}

		if (portStats != null) {
			// portStats = -1; //for testing
			if (portStats < 0 || portStats > 65535) {
				// issue:10869, the portStats is invalid, so this client socket can't open
				System.err.println("The statistics socket port " + portStats + " is invalid.");
				execStat = false;
			}
		} else {
			execStat = false;
		}
		boolean inOSGi = routines.system.BundleUtils.inOSGi();

		if (inOSGi) {
			java.util.Dictionary<String, Object> jobProperties = routines.system.BundleUtils.getJobProperties(jobName);

			if (jobProperties != null && jobProperties.get("context") != null) {
				contextStr = (String) jobProperties.get("context");
			}
		}

		try {
			// call job/subjob with an existing context, like: --context=production. if
			// without this parameter, there will use the default context instead.
			java.io.InputStream inContext = tzydfvq.class.getClassLoader()
					.getResourceAsStream("orange/tzydfvq_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = tzydfvq.class.getClassLoader()
						.getResourceAsStream("config/contexts/" + contextStr + ".properties");
			}
			if (inContext != null) {
				try {
					// defaultProps is in order to keep the original context value
					if (context != null && context.isEmpty()) {
						defaultProps.load(inContext);
						context = new ContextProperties(defaultProps);
					}
				} finally {
					inContext.close();
				}
			} else if (!isDefaultContext) {
				// print info and job continue to run, for case: context_param is not empty.
				System.err.println("Could not find the context " + contextStr);
			}

			if (!context_param.isEmpty()) {
				context.putAll(context_param);
				// set types for params from parentJobs
				for (Object key : context_param.keySet()) {
					String context_key = key.toString();
					String context_type = context_param.getContextType(context_key);
					context.setContextType(context_key, context_type);

				}
			}
			class ContextProcessing {
				private void processContext_0() {
				}

				public void processAllContext() {
					processContext_0();
				}
			}

			new ContextProcessing().processAllContext();
		} catch (java.io.IOException ie) {
			System.err.println("Could not load context " + contextStr);
			ie.printStackTrace();
		}

		// get context value from parent directly
		if (parentContextMap != null && !parentContextMap.isEmpty()) {
		}

		// Resume: init the resumeUtil
		resumeEntryMethodName = ResumeUtil.getResumeEntryMethodName(resuming_checkpoint_path);
		resumeUtil = new ResumeUtil(resuming_logs_dir_path, isChildJob, rootPid);
		resumeUtil.initCommonInfo(pid, rootPid, fatherPid, projectName, jobName, contextStr, jobVersion);

		List<String> parametersToEncrypt = new java.util.ArrayList<String>();
		// Resume: jobStart
		resumeUtil.addLog("JOB_STARTED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "",
				"", "", "", "", resumeUtil.convertToJsonText(context, parametersToEncrypt));

		if (execStat) {
			try {
				runStat.openSocket(!isChildJob);
				runStat.setAllPID(rootPid, fatherPid, pid, jobName);
				runStat.startThreadStat(clientHost, portStats);
				runStat.updateStatOnJob(RunStat.JOBSTART, fatherNode);
			} catch (java.io.IOException ioException) {
				ioException.printStackTrace();
			}
		}

		java.util.concurrent.ConcurrentHashMap<Object, Object> concurrentHashMap = new java.util.concurrent.ConcurrentHashMap<Object, Object>();
		globalMap.put("concurrentHashMap", concurrentHashMap);

		long startUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long endUsedMemory = 0;
		long end = 0;

		startTime = System.currentTimeMillis();

		this.globalResumeTicket = true;// to run tPreJob

		this.globalResumeTicket = false;// to run others jobs

		try {
			errorCode = null;
			tFileInputExcel_35Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tFileInputExcel_35) {
			globalMap.put("tFileInputExcel_35_SUBPROCESS_STATE", -1);

			e_tFileInputExcel_35.printStackTrace();

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if (false) {
			System.out.println((endUsedMemory - startUsedMemory) + " bytes memory increase when running : tzydfvq");
		}

		if (execStat) {
			runStat.updateStatOnJob(RunStat.JOBEND, fatherNode);
			runStat.stopThreadStat();
		}
		int returnCode = 0;

		if (errorCode == null) {
			returnCode = status != null && status.equals("failure") ? 1 : 0;
		} else {
			returnCode = errorCode.intValue();
		}
		resumeUtil.addLog("JOB_ENDED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "", "",
				"" + returnCode, "", "", "");

		return returnCode;

	}

	// only for OSGi env
	public void destroy() {

	}

	private java.util.Map<String, Object> getSharedConnections4REST() {
		java.util.Map<String, Object> connections = new java.util.HashMap<String, Object>();

		return connections;
	}

	private void evalParam(String arg) {
		if (arg.startsWith("--resuming_logs_dir_path")) {
			resuming_logs_dir_path = arg.substring(25);
		} else if (arg.startsWith("--resuming_checkpoint_path")) {
			resuming_checkpoint_path = arg.substring(27);
		} else if (arg.startsWith("--parent_part_launcher")) {
			parent_part_launcher = arg.substring(23);
		} else if (arg.startsWith("--watch")) {
			watch = true;
		} else if (arg.startsWith("--stat_port=")) {
			String portStatsStr = arg.substring(12);
			if (portStatsStr != null && !portStatsStr.equals("null")) {
				portStats = Integer.parseInt(portStatsStr);
			}
		} else if (arg.startsWith("--trace_port=")) {
			portTraces = Integer.parseInt(arg.substring(13));
		} else if (arg.startsWith("--client_host=")) {
			clientHost = arg.substring(14);
		} else if (arg.startsWith("--context=")) {
			contextStr = arg.substring(10);
			isDefaultContext = false;
		} else if (arg.startsWith("--father_pid=")) {
			fatherPid = arg.substring(13);
		} else if (arg.startsWith("--root_pid=")) {
			rootPid = arg.substring(11);
		} else if (arg.startsWith("--father_node=")) {
			fatherNode = arg.substring(14);
		} else if (arg.startsWith("--pid=")) {
			pid = arg.substring(6);
		} else if (arg.startsWith("--context_type")) {
			String keyValue = arg.substring(15);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.setContextType(keyValue.substring(0, index),
							replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.setContextType(keyValue.substring(0, index), keyValue.substring(index + 1));
				}

			}

		} else if (arg.startsWith("--context_param")) {
			String keyValue = arg.substring(16);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.put(keyValue.substring(0, index), replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.put(keyValue.substring(0, index), keyValue.substring(index + 1));
				}
			}
		} else if (arg.startsWith("--log4jLevel=")) {
			log4jLevel = arg.substring(13);
		} else if (arg.startsWith("--audit.enabled") && arg.contains("=")) {// for trunjob call
			final int equal = arg.indexOf('=');
			final String key = arg.substring("--".length(), equal);
			System.setProperty(key, arg.substring(equal + 1));
		}
	}

	private static final String NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY = "<TALEND_NULL>";

	private final String[][] escapeChars = { { "\\\\", "\\" }, { "\\n", "\n" }, { "\\'", "\'" }, { "\\r", "\r" },
			{ "\\f", "\f" }, { "\\b", "\b" }, { "\\t", "\t" } };

	private String replaceEscapeChars(String keyValue) {

		if (keyValue == null || ("").equals(keyValue.trim())) {
			return keyValue;
		}

		StringBuilder result = new StringBuilder();
		int currIndex = 0;
		while (currIndex < keyValue.length()) {
			int index = -1;
			// judege if the left string includes escape chars
			for (String[] strArray : escapeChars) {
				index = keyValue.indexOf(strArray[0], currIndex);
				if (index >= 0) {

					result.append(keyValue.substring(currIndex, index + strArray[0].length()).replace(strArray[0],
							strArray[1]));
					currIndex = index + strArray[0].length();
					break;
				}
			}
			// if the left string doesn't include escape chars, append the left into the
			// result
			if (index < 0) {
				result.append(keyValue.substring(currIndex));
				currIndex = currIndex + keyValue.length();
			}
		}

		return result.toString();
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public String getStatus() {
		return status;
	}

	ResumeUtil resumeUtil = null;
}
/************************************************************************************************
 * 385290 characters generated by Talend Open Studio for Data Integration on the
 * 13 mai 2022 à 22:13:56 CEST
 ************************************************************************************************/