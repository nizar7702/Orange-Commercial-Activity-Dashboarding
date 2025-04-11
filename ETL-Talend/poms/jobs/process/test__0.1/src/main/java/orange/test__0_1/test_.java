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

package orange.test__0_1;

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
 * Job: test_ Purpose: <br>
 * Description: <br>
 * 
 * @author user@talend.com
 * @version 8.0.1.20211109_1610
 * @status
 */
public class test_ implements TalendJob {

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
	private final String jobName = "test_";
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
					test_.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(test_.this, new Object[] { e, currentComponent, globalMap });
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

	public void tDBInput_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_6_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBOutput_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputExcel_3_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_5_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tUnite_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputExcel_4_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_4_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tLogRow_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tLogRow_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row4_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputExcel_3_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tLogRow_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public static class row4Struct implements routines.system.IPersistableComparableLookupRow<row4Struct> {
		final static byte[] commonByteArrayLock_ORANGE_test_ = new byte[0];
		static byte[] commonByteArray_ORANGE_test_ = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int type_resiliation_id;

		public int getType_resiliation_id() {
			return this.type_resiliation_id;
		}

		public String Type_Resiliation;

		public String getType_Resiliation() {
			return this.Type_Resiliation;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.Type_Resiliation == null) ? 0 : this.Type_Resiliation.hashCode());

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
			final row4Struct other = (row4Struct) obj;

			if (this.Type_Resiliation == null) {
				if (other.Type_Resiliation != null)
					return false;

			} else if (!this.Type_Resiliation.equals(other.Type_Resiliation))

				return false;

			return true;
		}

		public void copyDataTo(row4Struct other) {

			other.type_resiliation_id = this.type_resiliation_id;
			other.Type_Resiliation = this.Type_Resiliation;

		}

		public void copyKeysDataTo(row4Struct other) {

			other.Type_Resiliation = this.Type_Resiliation;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_test_.length) {
					if (length < 1024 && commonByteArray_ORANGE_test_.length == 0) {
						commonByteArray_ORANGE_test_ = new byte[1024];
					} else {
						commonByteArray_ORANGE_test_ = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_test_, 0, length);
				strReturn = new String(commonByteArray_ORANGE_test_, 0, length, utf8Charset);
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
				if (length > commonByteArray_ORANGE_test_.length) {
					if (length < 1024 && commonByteArray_ORANGE_test_.length == 0) {
						commonByteArray_ORANGE_test_ = new byte[1024];
					} else {
						commonByteArray_ORANGE_test_ = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_test_, 0, length);
				strReturn = new String(commonByteArray_ORANGE_test_, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_ORANGE_test_) {

				try {

					int length = 0;

					this.Type_Resiliation = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_test_) {

				try {

					int length = 0;

					this.Type_Resiliation = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Type_Resiliation, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Type_Resiliation, dos);

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

				this.type_resiliation_id = dis.readInt();

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

				this.type_resiliation_id = objectIn.readInt();

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				dos.writeInt(this.type_resiliation_id);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				objectOut.writeInt(this.type_resiliation_id);

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
			sb.append("type_resiliation_id=" + String.valueOf(type_resiliation_id));
			sb.append(",Type_Resiliation=" + Type_Resiliation);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row4Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.Type_Resiliation, other.Type_Resiliation);
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

	public void tDBInput_2Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBInput_2_SUBPROCESS_STATE", 0);

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

				row4Struct row4 = new row4Struct();

				/**
				 * [tAdvancedHash_row4 begin ] start
				 */

				ok_Hash.put("tAdvancedHash_row4", false);
				start_Hash.put("tAdvancedHash_row4", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_row4";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row4");
				}

				int tos_count_tAdvancedHash_row4 = 0;

				// connection name:row4
				// source node:tDBInput_2 - inputs:(after_tFileInputExcel_3) outputs:(row4,row4)
				// | target node:tAdvancedHash_row4 - inputs:(row4) outputs:()
				// linked node: tMap_6 - inputs:(row4,row3) outputs:(llll)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row4 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row4Struct> tHash_Lookup_row4 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row4Struct>getLookup(matchingModeEnum_row4);

				globalMap.put("tHash_Lookup_row4", tHash_Lookup_row4);

				/**
				 * [tAdvancedHash_row4 begin ] stop
				 */

				/**
				 * [tDBInput_2 begin ] start
				 */

				ok_Hash.put("tDBInput_2", false);
				start_Hash.put("tDBInput_2", System.currentTimeMillis());

				currentComponent = "tDBInput_2";

				int tos_count_tDBInput_2 = 0;

				org.talend.designer.components.util.mssql.MSSqlGenerateTimestampUtil mssqlGTU_tDBInput_2 = org.talend.designer.components.util.mssql.MSSqlUtilFactory
						.getMSSqlGenerateTimestampUtil();

				java.util.List<String> talendToDBList_tDBInput_2 = new java.util.ArrayList();
				String[] talendToDBArray_tDBInput_2 = new String[] { "FLOAT", "NUMERIC", "NUMERIC IDENTITY", "DECIMAL",
						"DECIMAL IDENTITY", "REAL" };
				java.util.Collections.addAll(talendToDBList_tDBInput_2, talendToDBArray_tDBInput_2);
				int nb_line_tDBInput_2 = 0;
				java.sql.Connection conn_tDBInput_2 = null;
				String driverClass_tDBInput_2 = "net.sourceforge.jtds.jdbc.Driver";
				java.lang.Class jdbcclazz_tDBInput_2 = java.lang.Class.forName(driverClass_tDBInput_2);
				String dbUser_tDBInput_2 = "sa";

				final String decryptedPassword_tDBInput_2 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:PXWmOnXoaF6BuI+uVDK5OhK9G3RxsRlBni0Hxbu80+MheLJLFb0=");

				String dbPwd_tDBInput_2 = decryptedPassword_tDBInput_2;

				String port_tDBInput_2 = "1433";
				String dbname_tDBInput_2 = "orange_DW__";
				String url_tDBInput_2 = "jdbc:jtds:sqlserver://" + "DESKTOP-6RL9E91";
				if (!"".equals(port_tDBInput_2)) {
					url_tDBInput_2 += ":" + "1433";
				}
				if (!"".equals(dbname_tDBInput_2)) {
					url_tDBInput_2 += "//" + "orange_DW__";
				}
				url_tDBInput_2 += ";appName=" + projectName + ";" + "";
				String dbschema_tDBInput_2 = "dbo";

				conn_tDBInput_2 = java.sql.DriverManager.getConnection(url_tDBInput_2, dbUser_tDBInput_2,
						dbPwd_tDBInput_2);

				java.sql.Statement stmt_tDBInput_2 = conn_tDBInput_2.createStatement();

				String dbquery_tDBInput_2 = "SELECT dbo.Dim_type.\"type_résiliation_id\",\n		dbo.Dim_type.Type_Resiliation\nFROM	dbo.Dim_type";

				globalMap.put("tDBInput_2_QUERY", dbquery_tDBInput_2);
				java.sql.ResultSet rs_tDBInput_2 = null;

				try {
					rs_tDBInput_2 = stmt_tDBInput_2.executeQuery(dbquery_tDBInput_2);
					java.sql.ResultSetMetaData rsmd_tDBInput_2 = rs_tDBInput_2.getMetaData();
					int colQtyInRs_tDBInput_2 = rsmd_tDBInput_2.getColumnCount();

					String tmpContent_tDBInput_2 = null;

					while (rs_tDBInput_2.next()) {
						nb_line_tDBInput_2++;

						if (colQtyInRs_tDBInput_2 < 1) {
							row4.type_resiliation_id = 0;
						} else {

							row4.type_resiliation_id = rs_tDBInput_2.getInt(1);
							if (rs_tDBInput_2.wasNull()) {
								throw new RuntimeException("Null value in non-Nullable column");
							}
						}
						if (colQtyInRs_tDBInput_2 < 2) {
							row4.Type_Resiliation = null;
						} else {

							tmpContent_tDBInput_2 = rs_tDBInput_2.getString(2);
							if (tmpContent_tDBInput_2 != null) {
								if (talendToDBList_tDBInput_2.contains(
										rsmd_tDBInput_2.getColumnTypeName(2).toUpperCase(java.util.Locale.ENGLISH))) {
									row4.Type_Resiliation = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
								} else {
									row4.Type_Resiliation = tmpContent_tDBInput_2;
								}
							} else {
								row4.Type_Resiliation = null;
							}
						}

						/**
						 * [tDBInput_2 begin ] stop
						 */

						/**
						 * [tDBInput_2 main ] start
						 */

						currentComponent = "tDBInput_2";

						tos_count_tDBInput_2++;

						/**
						 * [tDBInput_2 main ] stop
						 */

						/**
						 * [tDBInput_2 process_data_begin ] start
						 */

						currentComponent = "tDBInput_2";

						/**
						 * [tDBInput_2 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row4 main ] start
						 */

						currentComponent = "tAdvancedHash_row4";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row4"

							);
						}

						row4Struct row4_HashRow = new row4Struct();

						row4_HashRow.type_resiliation_id = row4.type_resiliation_id;

						row4_HashRow.Type_Resiliation = row4.Type_Resiliation;

						tHash_Lookup_row4.put(row4_HashRow);

						tos_count_tAdvancedHash_row4++;

						/**
						 * [tAdvancedHash_row4 main ] stop
						 */

						/**
						 * [tAdvancedHash_row4 process_data_begin ] start
						 */

						currentComponent = "tAdvancedHash_row4";

						/**
						 * [tAdvancedHash_row4 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row4 process_data_end ] start
						 */

						currentComponent = "tAdvancedHash_row4";

						/**
						 * [tAdvancedHash_row4 process_data_end ] stop
						 */

						/**
						 * [tDBInput_2 process_data_end ] start
						 */

						currentComponent = "tDBInput_2";

						/**
						 * [tDBInput_2 process_data_end ] stop
						 */

						/**
						 * [tDBInput_2 end ] start
						 */

						currentComponent = "tDBInput_2";

					}
				} finally {
					if (rs_tDBInput_2 != null) {
						rs_tDBInput_2.close();
					}
					if (stmt_tDBInput_2 != null) {
						stmt_tDBInput_2.close();
					}
					if (conn_tDBInput_2 != null && !conn_tDBInput_2.isClosed()) {

						conn_tDBInput_2.close();

						if ("com.mysql.cj.jdbc.Driver".equals((String) globalMap.get("driverClass_"))
								&& routines.system.BundleUtils.inOSGi()) {
							Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread")
									.getMethod("checkedShutdown").invoke(null, (Object[]) null);
						}

					}
				}
				globalMap.put("tDBInput_2_NB_LINE", nb_line_tDBInput_2);

				ok_Hash.put("tDBInput_2", true);
				end_Hash.put("tDBInput_2", System.currentTimeMillis());

				/**
				 * [tDBInput_2 end ] stop
				 */

				/**
				 * [tAdvancedHash_row4 end ] start
				 */

				currentComponent = "tAdvancedHash_row4";

				tHash_Lookup_row4.endPut();

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row4");
				}

				ok_Hash.put("tAdvancedHash_row4", true);
				end_Hash.put("tAdvancedHash_row4", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row4 end ] stop
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
				 * [tDBInput_2 finally ] start
				 */

				currentComponent = "tDBInput_2";

				/**
				 * [tDBInput_2 finally ] stop
				 */

				/**
				 * [tAdvancedHash_row4 finally ] start
				 */

				currentComponent = "tAdvancedHash_row4";

				/**
				 * [tAdvancedHash_row4 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBInput_2_SUBPROCESS_STATE", 1);
	}

	public static class llllStruct implements routines.system.IPersistableRow<llllStruct> {
		final static byte[] commonByteArrayLock_ORANGE_test_ = new byte[0];
		static byte[] commonByteArray_ORANGE_test_ = new byte[0];

		public String Type_Resiliation;

		public String getType_Resiliation() {
			return this.Type_Resiliation;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_test_.length) {
					if (length < 1024 && commonByteArray_ORANGE_test_.length == 0) {
						commonByteArray_ORANGE_test_ = new byte[1024];
					} else {
						commonByteArray_ORANGE_test_ = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_test_, 0, length);
				strReturn = new String(commonByteArray_ORANGE_test_, 0, length, utf8Charset);
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
				if (length > commonByteArray_ORANGE_test_.length) {
					if (length < 1024 && commonByteArray_ORANGE_test_.length == 0) {
						commonByteArray_ORANGE_test_ = new byte[1024];
					} else {
						commonByteArray_ORANGE_test_ = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_test_, 0, length);
				strReturn = new String(commonByteArray_ORANGE_test_, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_ORANGE_test_) {

				try {

					int length = 0;

					this.Type_Resiliation = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_test_) {

				try {

					int length = 0;

					this.Type_Resiliation = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Type_Resiliation, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Type_Resiliation, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Type_Resiliation=" + Type_Resiliation);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(llllStruct other) {

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

	public static class row3Struct implements routines.system.IPersistableRow<row3Struct> {
		final static byte[] commonByteArrayLock_ORANGE_test_ = new byte[0];
		static byte[] commonByteArray_ORANGE_test_ = new byte[0];

		public String Type_Resiliation;

		public String getType_Resiliation() {
			return this.Type_Resiliation;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_test_.length) {
					if (length < 1024 && commonByteArray_ORANGE_test_.length == 0) {
						commonByteArray_ORANGE_test_ = new byte[1024];
					} else {
						commonByteArray_ORANGE_test_ = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_test_, 0, length);
				strReturn = new String(commonByteArray_ORANGE_test_, 0, length, utf8Charset);
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
				if (length > commonByteArray_ORANGE_test_.length) {
					if (length < 1024 && commonByteArray_ORANGE_test_.length == 0) {
						commonByteArray_ORANGE_test_ = new byte[1024];
					} else {
						commonByteArray_ORANGE_test_ = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_test_, 0, length);
				strReturn = new String(commonByteArray_ORANGE_test_, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_ORANGE_test_) {

				try {

					int length = 0;

					this.Type_Resiliation = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_test_) {

				try {

					int length = 0;

					this.Type_Resiliation = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Type_Resiliation, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Type_Resiliation, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Type_Resiliation=" + Type_Resiliation);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row3Struct other) {

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

	public static class bbbbStruct implements routines.system.IPersistableRow<bbbbStruct> {
		final static byte[] commonByteArrayLock_ORANGE_test_ = new byte[0];
		static byte[] commonByteArray_ORANGE_test_ = new byte[0];

		public String Type_Resiliation;

		public String getType_Resiliation() {
			return this.Type_Resiliation;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_test_.length) {
					if (length < 1024 && commonByteArray_ORANGE_test_.length == 0) {
						commonByteArray_ORANGE_test_ = new byte[1024];
					} else {
						commonByteArray_ORANGE_test_ = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_test_, 0, length);
				strReturn = new String(commonByteArray_ORANGE_test_, 0, length, utf8Charset);
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
				if (length > commonByteArray_ORANGE_test_.length) {
					if (length < 1024 && commonByteArray_ORANGE_test_.length == 0) {
						commonByteArray_ORANGE_test_ = new byte[1024];
					} else {
						commonByteArray_ORANGE_test_ = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_test_, 0, length);
				strReturn = new String(commonByteArray_ORANGE_test_, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_ORANGE_test_) {

				try {

					int length = 0;

					this.Type_Resiliation = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_test_) {

				try {

					int length = 0;

					this.Type_Resiliation = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Type_Resiliation, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Type_Resiliation, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Type_Resiliation=" + Type_Resiliation);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(bbbbStruct other) {

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

	public static class row1Struct implements routines.system.IPersistableRow<row1Struct> {
		final static byte[] commonByteArrayLock_ORANGE_test_ = new byte[0];
		static byte[] commonByteArray_ORANGE_test_ = new byte[0];

		public Float Custcode;

		public Float getCustcode() {
			return this.Custcode;
		}

		public String Raison_Sociale;

		public String getRaison_Sociale() {
			return this.Raison_Sociale;
		}

		public String Groupe_Client;

		public String getGroupe_Client() {
			return this.Groupe_Client;
		}

		public String Account_Manager;

		public String getAccount_Manager() {
			return this.Account_Manager;
		}

		public String Equipe;

		public String getEquipe() {
			return this.Equipe;
		}

		public Integer Somme_de_Resiliation;

		public Integer getSomme_de_Resiliation() {
			return this.Somme_de_Resiliation;
		}

		public String Type_Resiliation;

		public String getType_Resiliation() {
			return this.Type_Resiliation;
		}

		public String Motif;

		public String getMotif() {
			return this.Motif;
		}

		public String DFE;

		public String getDFE() {
			return this.DFE;
		}

		public String Engage_Oui_Non;

		public String getEngage_Oui_Non() {
			return this.Engage_Oui_Non;
		}

		public String Date_Depot;

		public String getDate_Depot() {
			return this.Date_Depot;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_test_.length) {
					if (length < 1024 && commonByteArray_ORANGE_test_.length == 0) {
						commonByteArray_ORANGE_test_ = new byte[1024];
					} else {
						commonByteArray_ORANGE_test_ = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_test_, 0, length);
				strReturn = new String(commonByteArray_ORANGE_test_, 0, length, utf8Charset);
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
				if (length > commonByteArray_ORANGE_test_.length) {
					if (length < 1024 && commonByteArray_ORANGE_test_.length == 0) {
						commonByteArray_ORANGE_test_ = new byte[1024];
					} else {
						commonByteArray_ORANGE_test_ = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_test_, 0, length);
				strReturn = new String(commonByteArray_ORANGE_test_, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_ORANGE_test_) {

				try {

					int length = 0;

					length = dis.readByte();
					if (length == -1) {
						this.Custcode = null;
					} else {
						this.Custcode = dis.readFloat();
					}

					this.Raison_Sociale = readString(dis);

					this.Groupe_Client = readString(dis);

					this.Account_Manager = readString(dis);

					this.Equipe = readString(dis);

					this.Somme_de_Resiliation = readInteger(dis);

					this.Type_Resiliation = readString(dis);

					this.Motif = readString(dis);

					this.DFE = readString(dis);

					this.Engage_Oui_Non = readString(dis);

					this.Date_Depot = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_test_) {

				try {

					int length = 0;

					length = dis.readByte();
					if (length == -1) {
						this.Custcode = null;
					} else {
						this.Custcode = dis.readFloat();
					}

					this.Raison_Sociale = readString(dis);

					this.Groupe_Client = readString(dis);

					this.Account_Manager = readString(dis);

					this.Equipe = readString(dis);

					this.Somme_de_Resiliation = readInteger(dis);

					this.Type_Resiliation = readString(dis);

					this.Motif = readString(dis);

					this.DFE = readString(dis);

					this.Engage_Oui_Non = readString(dis);

					this.Date_Depot = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Float

				if (this.Custcode == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Custcode);
				}

				// String

				writeString(this.Raison_Sociale, dos);

				// String

				writeString(this.Groupe_Client, dos);

				// String

				writeString(this.Account_Manager, dos);

				// String

				writeString(this.Equipe, dos);

				// Integer

				writeInteger(this.Somme_de_Resiliation, dos);

				// String

				writeString(this.Type_Resiliation, dos);

				// String

				writeString(this.Motif, dos);

				// String

				writeString(this.DFE, dos);

				// String

				writeString(this.Engage_Oui_Non, dos);

				// String

				writeString(this.Date_Depot, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Float

				if (this.Custcode == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Custcode);
				}

				// String

				writeString(this.Raison_Sociale, dos);

				// String

				writeString(this.Groupe_Client, dos);

				// String

				writeString(this.Account_Manager, dos);

				// String

				writeString(this.Equipe, dos);

				// Integer

				writeInteger(this.Somme_de_Resiliation, dos);

				// String

				writeString(this.Type_Resiliation, dos);

				// String

				writeString(this.Motif, dos);

				// String

				writeString(this.DFE, dos);

				// String

				writeString(this.Engage_Oui_Non, dos);

				// String

				writeString(this.Date_Depot, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Custcode=" + String.valueOf(Custcode));
			sb.append(",Raison_Sociale=" + Raison_Sociale);
			sb.append(",Groupe_Client=" + Groupe_Client);
			sb.append(",Account_Manager=" + Account_Manager);
			sb.append(",Equipe=" + Equipe);
			sb.append(",Somme_de_Resiliation=" + String.valueOf(Somme_de_Resiliation));
			sb.append(",Type_Resiliation=" + Type_Resiliation);
			sb.append(",Motif=" + Motif);
			sb.append(",DFE=" + DFE);
			sb.append(",Engage_Oui_Non=" + Engage_Oui_Non);
			sb.append(",Date_Depot=" + Date_Depot);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row1Struct other) {

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

	public static class after_tFileInputExcel_3Struct
			implements routines.system.IPersistableRow<after_tFileInputExcel_3Struct> {
		final static byte[] commonByteArrayLock_ORANGE_test_ = new byte[0];
		static byte[] commonByteArray_ORANGE_test_ = new byte[0];

		public Float Custcode;

		public Float getCustcode() {
			return this.Custcode;
		}

		public String Raison_Sociale;

		public String getRaison_Sociale() {
			return this.Raison_Sociale;
		}

		public String Groupe_Client;

		public String getGroupe_Client() {
			return this.Groupe_Client;
		}

		public String Account_Manager;

		public String getAccount_Manager() {
			return this.Account_Manager;
		}

		public String Equipe;

		public String getEquipe() {
			return this.Equipe;
		}

		public Integer Somme_de_Resiliation;

		public Integer getSomme_de_Resiliation() {
			return this.Somme_de_Resiliation;
		}

		public String Type_Resiliation;

		public String getType_Resiliation() {
			return this.Type_Resiliation;
		}

		public String Motif;

		public String getMotif() {
			return this.Motif;
		}

		public String DFE;

		public String getDFE() {
			return this.DFE;
		}

		public String Engage_Oui_Non;

		public String getEngage_Oui_Non() {
			return this.Engage_Oui_Non;
		}

		public String Date_Depot;

		public String getDate_Depot() {
			return this.Date_Depot;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_test_.length) {
					if (length < 1024 && commonByteArray_ORANGE_test_.length == 0) {
						commonByteArray_ORANGE_test_ = new byte[1024];
					} else {
						commonByteArray_ORANGE_test_ = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_test_, 0, length);
				strReturn = new String(commonByteArray_ORANGE_test_, 0, length, utf8Charset);
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
				if (length > commonByteArray_ORANGE_test_.length) {
					if (length < 1024 && commonByteArray_ORANGE_test_.length == 0) {
						commonByteArray_ORANGE_test_ = new byte[1024];
					} else {
						commonByteArray_ORANGE_test_ = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_test_, 0, length);
				strReturn = new String(commonByteArray_ORANGE_test_, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_ORANGE_test_) {

				try {

					int length = 0;

					length = dis.readByte();
					if (length == -1) {
						this.Custcode = null;
					} else {
						this.Custcode = dis.readFloat();
					}

					this.Raison_Sociale = readString(dis);

					this.Groupe_Client = readString(dis);

					this.Account_Manager = readString(dis);

					this.Equipe = readString(dis);

					this.Somme_de_Resiliation = readInteger(dis);

					this.Type_Resiliation = readString(dis);

					this.Motif = readString(dis);

					this.DFE = readString(dis);

					this.Engage_Oui_Non = readString(dis);

					this.Date_Depot = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_test_) {

				try {

					int length = 0;

					length = dis.readByte();
					if (length == -1) {
						this.Custcode = null;
					} else {
						this.Custcode = dis.readFloat();
					}

					this.Raison_Sociale = readString(dis);

					this.Groupe_Client = readString(dis);

					this.Account_Manager = readString(dis);

					this.Equipe = readString(dis);

					this.Somme_de_Resiliation = readInteger(dis);

					this.Type_Resiliation = readString(dis);

					this.Motif = readString(dis);

					this.DFE = readString(dis);

					this.Engage_Oui_Non = readString(dis);

					this.Date_Depot = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Float

				if (this.Custcode == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Custcode);
				}

				// String

				writeString(this.Raison_Sociale, dos);

				// String

				writeString(this.Groupe_Client, dos);

				// String

				writeString(this.Account_Manager, dos);

				// String

				writeString(this.Equipe, dos);

				// Integer

				writeInteger(this.Somme_de_Resiliation, dos);

				// String

				writeString(this.Type_Resiliation, dos);

				// String

				writeString(this.Motif, dos);

				// String

				writeString(this.DFE, dos);

				// String

				writeString(this.Engage_Oui_Non, dos);

				// String

				writeString(this.Date_Depot, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Float

				if (this.Custcode == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Custcode);
				}

				// String

				writeString(this.Raison_Sociale, dos);

				// String

				writeString(this.Groupe_Client, dos);

				// String

				writeString(this.Account_Manager, dos);

				// String

				writeString(this.Equipe, dos);

				// Integer

				writeInteger(this.Somme_de_Resiliation, dos);

				// String

				writeString(this.Type_Resiliation, dos);

				// String

				writeString(this.Motif, dos);

				// String

				writeString(this.DFE, dos);

				// String

				writeString(this.Engage_Oui_Non, dos);

				// String

				writeString(this.Date_Depot, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Custcode=" + String.valueOf(Custcode));
			sb.append(",Raison_Sociale=" + Raison_Sociale);
			sb.append(",Groupe_Client=" + Groupe_Client);
			sb.append(",Account_Manager=" + Account_Manager);
			sb.append(",Equipe=" + Equipe);
			sb.append(",Somme_de_Resiliation=" + String.valueOf(Somme_de_Resiliation));
			sb.append(",Type_Resiliation=" + Type_Resiliation);
			sb.append(",Motif=" + Motif);
			sb.append(",DFE=" + DFE);
			sb.append(",Engage_Oui_Non=" + Engage_Oui_Non);
			sb.append(",Date_Depot=" + Date_Depot);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(after_tFileInputExcel_3Struct other) {

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

	public static class aaaaStruct implements routines.system.IPersistableRow<aaaaStruct> {
		final static byte[] commonByteArrayLock_ORANGE_test_ = new byte[0];
		static byte[] commonByteArray_ORANGE_test_ = new byte[0];

		public String Type_de_suspension;

		public String getType_de_suspension() {
			return this.Type_de_suspension;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_test_.length) {
					if (length < 1024 && commonByteArray_ORANGE_test_.length == 0) {
						commonByteArray_ORANGE_test_ = new byte[1024];
					} else {
						commonByteArray_ORANGE_test_ = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_test_, 0, length);
				strReturn = new String(commonByteArray_ORANGE_test_, 0, length, utf8Charset);
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
				if (length > commonByteArray_ORANGE_test_.length) {
					if (length < 1024 && commonByteArray_ORANGE_test_.length == 0) {
						commonByteArray_ORANGE_test_ = new byte[1024];
					} else {
						commonByteArray_ORANGE_test_ = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_test_, 0, length);
				strReturn = new String(commonByteArray_ORANGE_test_, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_ORANGE_test_) {

				try {

					int length = 0;

					this.Type_de_suspension = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_test_) {

				try {

					int length = 0;

					this.Type_de_suspension = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Type_de_suspension, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Type_de_suspension, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Type_de_suspension=" + Type_de_suspension);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(aaaaStruct other) {

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

	public static class row2Struct implements routines.system.IPersistableRow<row2Struct> {
		final static byte[] commonByteArrayLock_ORANGE_test_ = new byte[0];
		static byte[] commonByteArray_ORANGE_test_ = new byte[0];

		public Float Custcode;

		public Float getCustcode() {
			return this.Custcode;
		}

		public String Raison_Sociale;

		public String getRaison_Sociale() {
			return this.Raison_Sociale;
		}

		public String Groupe_Client;

		public String getGroupe_Client() {
			return this.Groupe_Client;
		}

		public String Account_Manager;

		public String getAccount_Manager() {
			return this.Account_Manager;
		}

		public String Equipe;

		public String getEquipe() {
			return this.Equipe;
		}

		public Integer Somme_de_Suspension;

		public Integer getSomme_de_Suspension() {
			return this.Somme_de_Suspension;
		}

		public String Type_de_suspension;

		public String getType_de_suspension() {
			return this.Type_de_suspension;
		}

		public String Motif;

		public String getMotif() {
			return this.Motif;
		}

		public String DFE;

		public String getDFE() {
			return this.DFE;
		}

		public String Engage_Oui_Non;

		public String getEngage_Oui_Non() {
			return this.Engage_Oui_Non;
		}

		public String Date_Depot;

		public String getDate_Depot() {
			return this.Date_Depot;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_test_.length) {
					if (length < 1024 && commonByteArray_ORANGE_test_.length == 0) {
						commonByteArray_ORANGE_test_ = new byte[1024];
					} else {
						commonByteArray_ORANGE_test_ = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_test_, 0, length);
				strReturn = new String(commonByteArray_ORANGE_test_, 0, length, utf8Charset);
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
				if (length > commonByteArray_ORANGE_test_.length) {
					if (length < 1024 && commonByteArray_ORANGE_test_.length == 0) {
						commonByteArray_ORANGE_test_ = new byte[1024];
					} else {
						commonByteArray_ORANGE_test_ = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_test_, 0, length);
				strReturn = new String(commonByteArray_ORANGE_test_, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_ORANGE_test_) {

				try {

					int length = 0;

					length = dis.readByte();
					if (length == -1) {
						this.Custcode = null;
					} else {
						this.Custcode = dis.readFloat();
					}

					this.Raison_Sociale = readString(dis);

					this.Groupe_Client = readString(dis);

					this.Account_Manager = readString(dis);

					this.Equipe = readString(dis);

					this.Somme_de_Suspension = readInteger(dis);

					this.Type_de_suspension = readString(dis);

					this.Motif = readString(dis);

					this.DFE = readString(dis);

					this.Engage_Oui_Non = readString(dis);

					this.Date_Depot = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_test_) {

				try {

					int length = 0;

					length = dis.readByte();
					if (length == -1) {
						this.Custcode = null;
					} else {
						this.Custcode = dis.readFloat();
					}

					this.Raison_Sociale = readString(dis);

					this.Groupe_Client = readString(dis);

					this.Account_Manager = readString(dis);

					this.Equipe = readString(dis);

					this.Somme_de_Suspension = readInteger(dis);

					this.Type_de_suspension = readString(dis);

					this.Motif = readString(dis);

					this.DFE = readString(dis);

					this.Engage_Oui_Non = readString(dis);

					this.Date_Depot = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Float

				if (this.Custcode == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Custcode);
				}

				// String

				writeString(this.Raison_Sociale, dos);

				// String

				writeString(this.Groupe_Client, dos);

				// String

				writeString(this.Account_Manager, dos);

				// String

				writeString(this.Equipe, dos);

				// Integer

				writeInteger(this.Somme_de_Suspension, dos);

				// String

				writeString(this.Type_de_suspension, dos);

				// String

				writeString(this.Motif, dos);

				// String

				writeString(this.DFE, dos);

				// String

				writeString(this.Engage_Oui_Non, dos);

				// String

				writeString(this.Date_Depot, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Float

				if (this.Custcode == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Custcode);
				}

				// String

				writeString(this.Raison_Sociale, dos);

				// String

				writeString(this.Groupe_Client, dos);

				// String

				writeString(this.Account_Manager, dos);

				// String

				writeString(this.Equipe, dos);

				// Integer

				writeInteger(this.Somme_de_Suspension, dos);

				// String

				writeString(this.Type_de_suspension, dos);

				// String

				writeString(this.Motif, dos);

				// String

				writeString(this.DFE, dos);

				// String

				writeString(this.Engage_Oui_Non, dos);

				// String

				writeString(this.Date_Depot, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Custcode=" + String.valueOf(Custcode));
			sb.append(",Raison_Sociale=" + Raison_Sociale);
			sb.append(",Groupe_Client=" + Groupe_Client);
			sb.append(",Account_Manager=" + Account_Manager);
			sb.append(",Equipe=" + Equipe);
			sb.append(",Somme_de_Suspension=" + String.valueOf(Somme_de_Suspension));
			sb.append(",Type_de_suspension=" + Type_de_suspension);
			sb.append(",Motif=" + Motif);
			sb.append(",DFE=" + DFE);
			sb.append(",Engage_Oui_Non=" + Engage_Oui_Non);
			sb.append(",Date_Depot=" + Date_Depot);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row2Struct other) {

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

	public void tFileInputExcel_3Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputExcel_3_SUBPROCESS_STATE", 0);

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

				tDBInput_2Process(globalMap);

				row1Struct row1 = new row1Struct();
				bbbbStruct bbbb = new bbbbStruct();

				row2Struct row2 = new row2Struct();
				aaaaStruct aaaa = new aaaaStruct();

				row3Struct row3 = new row3Struct();
				llllStruct llll = new llllStruct();

				/**
				 * [tDBOutput_2 begin ] start
				 */

				ok_Hash.put("tDBOutput_2", false);
				start_Hash.put("tDBOutput_2", System.currentTimeMillis());

				currentComponent = "tDBOutput_2";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "llll");
				}

				int tos_count_tDBOutput_2 = 0;

				int nb_line_tDBOutput_2 = 0;
				int nb_line_update_tDBOutput_2 = 0;
				int nb_line_inserted_tDBOutput_2 = 0;
				int nb_line_deleted_tDBOutput_2 = 0;
				int nb_line_rejected_tDBOutput_2 = 0;

				int deletedCount_tDBOutput_2 = 0;
				int updatedCount_tDBOutput_2 = 0;
				int insertedCount_tDBOutput_2 = 0;
				int rowsToCommitCount_tDBOutput_2 = 0;
				int rejectedCount_tDBOutput_2 = 0;
				String dbschema_tDBOutput_2 = null;
				String tableName_tDBOutput_2 = null;
				boolean whetherReject_tDBOutput_2 = false;

				java.util.Calendar calendar_tDBOutput_2 = java.util.Calendar.getInstance();
				long year1_tDBOutput_2 = TalendDate.parseDate("yyyy-MM-dd", "0001-01-01").getTime();
				long year2_tDBOutput_2 = TalendDate.parseDate("yyyy-MM-dd", "1753-01-01").getTime();
				long year10000_tDBOutput_2 = TalendDate.parseDate("yyyy-MM-dd HH:mm:ss", "9999-12-31 24:00:00")
						.getTime();
				long date_tDBOutput_2;

				java.util.Calendar calendar_datetimeoffset_tDBOutput_2 = java.util.Calendar
						.getInstance(java.util.TimeZone.getTimeZone("UTC"));

				java.sql.Connection conn_tDBOutput_2 = null;
				String dbUser_tDBOutput_2 = null;
				dbschema_tDBOutput_2 = "dbo";
				String driverClass_tDBOutput_2 = "net.sourceforge.jtds.jdbc.Driver";

				java.lang.Class.forName(driverClass_tDBOutput_2);
				String port_tDBOutput_2 = "1433";
				String dbname_tDBOutput_2 = "orange_DW__";
				String url_tDBOutput_2 = "jdbc:jtds:sqlserver://" + "DESKTOP-6RL9E91";
				if (!"".equals(port_tDBOutput_2)) {
					url_tDBOutput_2 += ":" + "1433";
				}
				if (!"".equals(dbname_tDBOutput_2)) {
					url_tDBOutput_2 += "//" + "orange_DW__";

				}
				url_tDBOutput_2 += ";appName=" + projectName + ";" + "";
				dbUser_tDBOutput_2 = "sa";

				final String decryptedPassword_tDBOutput_2 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:FzZeDHF874X7dBqVd4TaObFltOlLS5iA7hDwKTqFGlxPzMq9Sg4=");

				String dbPwd_tDBOutput_2 = decryptedPassword_tDBOutput_2;
				conn_tDBOutput_2 = java.sql.DriverManager.getConnection(url_tDBOutput_2, dbUser_tDBOutput_2,
						dbPwd_tDBOutput_2);

				resourceMap.put("conn_tDBOutput_2", conn_tDBOutput_2);

				conn_tDBOutput_2.setAutoCommit(false);
				int commitEvery_tDBOutput_2 = 10000;
				int commitCounter_tDBOutput_2 = 0;

				int batchSize_tDBOutput_2 = 10000;
				int batchSizeCounter_tDBOutput_2 = 0;

				if (dbschema_tDBOutput_2 == null || dbschema_tDBOutput_2.trim().length() == 0) {
					tableName_tDBOutput_2 = "Dim_type";
				} else {
					tableName_tDBOutput_2 = dbschema_tDBOutput_2 + "].[" + "Dim_type";
				}
				int count_tDBOutput_2 = 0;

				String insert_tDBOutput_2 = "INSERT INTO [" + tableName_tDBOutput_2
						+ "] ([Type_Resiliation]) VALUES (?)";
				java.sql.PreparedStatement pstmt_tDBOutput_2 = conn_tDBOutput_2.prepareStatement(insert_tDBOutput_2);
				resourceMap.put("pstmt_tDBOutput_2", pstmt_tDBOutput_2);

				/**
				 * [tDBOutput_2 begin ] stop
				 */

				/**
				 * [tMap_6 begin ] start
				 */

				ok_Hash.put("tMap_6", false);
				start_Hash.put("tMap_6", System.currentTimeMillis());

				currentComponent = "tMap_6";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row3");
				}

				int tos_count_tMap_6 = 0;

// ###############################
// # Lookup's keys initialization

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row4Struct> tHash_Lookup_row4 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row4Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row4Struct>) globalMap
						.get("tHash_Lookup_row4"));

				row4Struct row4HashKey = new row4Struct();
				row4Struct row4Default = new row4Struct();
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_6__Struct {
				}
				Var__tMap_6__Struct Var__tMap_6 = new Var__tMap_6__Struct();
// ###############################

// ###############################
// # Outputs initialization
				llllStruct llll_tmp = new llllStruct();
// ###############################

				/**
				 * [tMap_6 begin ] stop
				 */

				/**
				 * [tUnite_2 begin ] start
				 */

				ok_Hash.put("tUnite_2", false);
				start_Hash.put("tUnite_2", System.currentTimeMillis());

				currentComponent = "tUnite_2";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "bbbb", "aaaa");
				}

				int tos_count_tUnite_2 = 0;

				int nb_line_tUnite_2 = 0;

				/**
				 * [tUnite_2 begin ] stop
				 */

				/**
				 * [tMap_5 begin ] start
				 */

				ok_Hash.put("tMap_5", false);
				start_Hash.put("tMap_5", System.currentTimeMillis());

				currentComponent = "tMap_5";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row1");
				}

				int tos_count_tMap_5 = 0;

// ###############################
// # Lookup's keys initialization
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_5__Struct {
				}
				Var__tMap_5__Struct Var__tMap_5 = new Var__tMap_5__Struct();
// ###############################

// ###############################
// # Outputs initialization
				bbbbStruct bbbb_tmp = new bbbbStruct();
// ###############################

				/**
				 * [tMap_5 begin ] stop
				 */

				/**
				 * [tFileInputExcel_3 begin ] start
				 */

				ok_Hash.put("tFileInputExcel_3", false);
				start_Hash.put("tFileInputExcel_3", System.currentTimeMillis());

				currentComponent = "tFileInputExcel_3";

				int tos_count_tFileInputExcel_3 = 0;

				final String decryptedPassword_tFileInputExcel_3 = routines.system.PasswordEncryptUtil
						.decryptPassword("enc:routine.encryption.key.v1:ava5zlf1ANCBj+ct449AFGaYq0+zvHdT6UTTvg==");
				String password_tFileInputExcel_3 = decryptedPassword_tFileInputExcel_3;
				if (password_tFileInputExcel_3.isEmpty()) {
					password_tFileInputExcel_3 = null;
				}
				class RegexUtil_tFileInputExcel_3 {

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
				RegexUtil_tFileInputExcel_3 regexUtil_tFileInputExcel_3 = new RegexUtil_tFileInputExcel_3();

				Object source_tFileInputExcel_3 = "C:/Users/DELL/Downloads/parc facturé Mars 2022 (1).xlsx";
				org.apache.poi.xssf.usermodel.XSSFWorkbook workbook_tFileInputExcel_3 = null;

				if (source_tFileInputExcel_3 instanceof String) {
					workbook_tFileInputExcel_3 = (org.apache.poi.xssf.usermodel.XSSFWorkbook) org.apache.poi.ss.usermodel.WorkbookFactory
							.create(new java.io.File((String) source_tFileInputExcel_3), password_tFileInputExcel_3,
									true);
				} else if (source_tFileInputExcel_3 instanceof java.io.InputStream) {
					workbook_tFileInputExcel_3 = (org.apache.poi.xssf.usermodel.XSSFWorkbook) org.apache.poi.ss.usermodel.WorkbookFactory
							.create((java.io.InputStream) source_tFileInputExcel_3, password_tFileInputExcel_3);
				} else {
					workbook_tFileInputExcel_3 = null;
					throw new java.lang.Exception("The data source should be specified as Inputstream or File Path!");
				}
				try {

					java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> sheetList_tFileInputExcel_3 = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
					sheetList_tFileInputExcel_3.addAll(
							regexUtil_tFileInputExcel_3.getSheets(workbook_tFileInputExcel_3, "Résiliation", false));
					if (sheetList_tFileInputExcel_3.size() <= 0) {
						throw new RuntimeException("Special sheets not exist!");
					}

					java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> sheetList_FilterNull_tFileInputExcel_3 = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
					for (org.apache.poi.xssf.usermodel.XSSFSheet sheet_FilterNull_tFileInputExcel_3 : sheetList_tFileInputExcel_3) {
						if (sheet_FilterNull_tFileInputExcel_3 != null
								&& sheetList_FilterNull_tFileInputExcel_3.iterator() != null
								&& sheet_FilterNull_tFileInputExcel_3.iterator().hasNext()) {
							sheetList_FilterNull_tFileInputExcel_3.add(sheet_FilterNull_tFileInputExcel_3);
						}
					}
					sheetList_tFileInputExcel_3 = sheetList_FilterNull_tFileInputExcel_3;
					if (sheetList_tFileInputExcel_3.size() > 0) {
						int nb_line_tFileInputExcel_3 = 0;

						int begin_line_tFileInputExcel_3 = 1;

						int footer_input_tFileInputExcel_3 = 0;

						int end_line_tFileInputExcel_3 = 0;
						for (org.apache.poi.xssf.usermodel.XSSFSheet sheet_tFileInputExcel_3 : sheetList_tFileInputExcel_3) {
							end_line_tFileInputExcel_3 += (sheet_tFileInputExcel_3.getLastRowNum() + 1);
						}
						end_line_tFileInputExcel_3 -= footer_input_tFileInputExcel_3;
						int limit_tFileInputExcel_3 = -1;
						int start_column_tFileInputExcel_3 = 1 - 1;
						int end_column_tFileInputExcel_3 = -1;

						org.apache.poi.xssf.usermodel.XSSFRow row_tFileInputExcel_3 = null;
						org.apache.poi.xssf.usermodel.XSSFSheet sheet_tFileInputExcel_3 = sheetList_tFileInputExcel_3
								.get(0);
						int rowCount_tFileInputExcel_3 = 0;
						int sheetIndex_tFileInputExcel_3 = 0;
						int currentRows_tFileInputExcel_3 = (sheetList_tFileInputExcel_3.get(0).getLastRowNum() + 1);

						// for the number format
						java.text.DecimalFormat df_tFileInputExcel_3 = new java.text.DecimalFormat(
								"#.####################################");
						char decimalChar_tFileInputExcel_3 = df_tFileInputExcel_3.getDecimalFormatSymbols()
								.getDecimalSeparator();

						for (int i_tFileInputExcel_3 = begin_line_tFileInputExcel_3; i_tFileInputExcel_3 < end_line_tFileInputExcel_3; i_tFileInputExcel_3++) {

							int emptyColumnCount_tFileInputExcel_3 = 0;

							if (limit_tFileInputExcel_3 != -1 && nb_line_tFileInputExcel_3 >= limit_tFileInputExcel_3) {
								break;
							}

							while (i_tFileInputExcel_3 >= rowCount_tFileInputExcel_3 + currentRows_tFileInputExcel_3) {
								rowCount_tFileInputExcel_3 += currentRows_tFileInputExcel_3;
								sheet_tFileInputExcel_3 = sheetList_tFileInputExcel_3
										.get(++sheetIndex_tFileInputExcel_3);
								currentRows_tFileInputExcel_3 = (sheet_tFileInputExcel_3.getLastRowNum() + 1);
							}
							globalMap.put("tFileInputExcel_3_CURRENT_SHEET", sheet_tFileInputExcel_3.getSheetName());
							if (rowCount_tFileInputExcel_3 <= i_tFileInputExcel_3) {
								row_tFileInputExcel_3 = sheet_tFileInputExcel_3
										.getRow(i_tFileInputExcel_3 - rowCount_tFileInputExcel_3);
							}
							row1 = null;
							int tempRowLength_tFileInputExcel_3 = 11;

							int columnIndex_tFileInputExcel_3 = 0;

							String[] temp_row_tFileInputExcel_3 = new String[tempRowLength_tFileInputExcel_3];
							int excel_end_column_tFileInputExcel_3;
							if (row_tFileInputExcel_3 == null) {
								excel_end_column_tFileInputExcel_3 = 0;
							} else {
								excel_end_column_tFileInputExcel_3 = row_tFileInputExcel_3.getLastCellNum();
							}
							int actual_end_column_tFileInputExcel_3;
							if (end_column_tFileInputExcel_3 == -1) {
								actual_end_column_tFileInputExcel_3 = excel_end_column_tFileInputExcel_3;
							} else {
								actual_end_column_tFileInputExcel_3 = end_column_tFileInputExcel_3 > excel_end_column_tFileInputExcel_3
										? excel_end_column_tFileInputExcel_3
										: end_column_tFileInputExcel_3;
							}
							org.apache.poi.ss.formula.eval.NumberEval ne_tFileInputExcel_3 = null;
							for (int i = 0; i < tempRowLength_tFileInputExcel_3; i++) {
								if (i + start_column_tFileInputExcel_3 < actual_end_column_tFileInputExcel_3) {
									org.apache.poi.ss.usermodel.Cell cell_tFileInputExcel_3 = row_tFileInputExcel_3
											.getCell(i + start_column_tFileInputExcel_3);
									if (cell_tFileInputExcel_3 != null) {
										switch (cell_tFileInputExcel_3.getCellType()) {
										case STRING:
											temp_row_tFileInputExcel_3[i] = cell_tFileInputExcel_3
													.getRichStringCellValue().getString();
											break;
										case NUMERIC:
											if (org.apache.poi.ss.usermodel.DateUtil
													.isCellDateFormatted(cell_tFileInputExcel_3)) {
												temp_row_tFileInputExcel_3[i] = cell_tFileInputExcel_3
														.getDateCellValue().toString();
											} else {
												temp_row_tFileInputExcel_3[i] = df_tFileInputExcel_3
														.format(cell_tFileInputExcel_3.getNumericCellValue());
											}
											break;
										case BOOLEAN:
											temp_row_tFileInputExcel_3[i] = String
													.valueOf(cell_tFileInputExcel_3.getBooleanCellValue());
											break;
										case FORMULA:
											switch (cell_tFileInputExcel_3.getCachedFormulaResultType()) {
											case STRING:
												temp_row_tFileInputExcel_3[i] = cell_tFileInputExcel_3
														.getRichStringCellValue().getString();
												break;
											case NUMERIC:
												if (org.apache.poi.ss.usermodel.DateUtil
														.isCellDateFormatted(cell_tFileInputExcel_3)) {
													temp_row_tFileInputExcel_3[i] = cell_tFileInputExcel_3
															.getDateCellValue().toString();
												} else {
													ne_tFileInputExcel_3 = new org.apache.poi.ss.formula.eval.NumberEval(
															cell_tFileInputExcel_3.getNumericCellValue());
													temp_row_tFileInputExcel_3[i] = ne_tFileInputExcel_3
															.getStringValue();
												}
												break;
											case BOOLEAN:
												temp_row_tFileInputExcel_3[i] = String
														.valueOf(cell_tFileInputExcel_3.getBooleanCellValue());
												break;
											default:
												temp_row_tFileInputExcel_3[i] = "";
											}
											break;
										default:
											temp_row_tFileInputExcel_3[i] = "";
										}
									} else {
										temp_row_tFileInputExcel_3[i] = "";
									}

								} else {
									temp_row_tFileInputExcel_3[i] = "";
								}
							}
							boolean whetherReject_tFileInputExcel_3 = false;
							row1 = new row1Struct();
							int curColNum_tFileInputExcel_3 = -1;
							String curColName_tFileInputExcel_3 = "";
							try {
								columnIndex_tFileInputExcel_3 = 0;

								if (temp_row_tFileInputExcel_3[columnIndex_tFileInputExcel_3].length() > 0) {
									curColNum_tFileInputExcel_3 = columnIndex_tFileInputExcel_3
											+ start_column_tFileInputExcel_3 + 1;
									curColName_tFileInputExcel_3 = "Custcode";

									row1.Custcode = ParserUtils.parseTo_Float(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_3[columnIndex_tFileInputExcel_3], null,
											'.' == decimalChar_tFileInputExcel_3 ? null
													: decimalChar_tFileInputExcel_3));
								} else {
									row1.Custcode = null;
									emptyColumnCount_tFileInputExcel_3++;
								}
								columnIndex_tFileInputExcel_3 = 1;

								if (temp_row_tFileInputExcel_3[columnIndex_tFileInputExcel_3].length() > 0) {
									curColNum_tFileInputExcel_3 = columnIndex_tFileInputExcel_3
											+ start_column_tFileInputExcel_3 + 1;
									curColName_tFileInputExcel_3 = "Raison_Sociale";

									row1.Raison_Sociale = temp_row_tFileInputExcel_3[columnIndex_tFileInputExcel_3];
								} else {
									row1.Raison_Sociale = null;
									emptyColumnCount_tFileInputExcel_3++;
								}
								columnIndex_tFileInputExcel_3 = 2;

								if (temp_row_tFileInputExcel_3[columnIndex_tFileInputExcel_3].length() > 0) {
									curColNum_tFileInputExcel_3 = columnIndex_tFileInputExcel_3
											+ start_column_tFileInputExcel_3 + 1;
									curColName_tFileInputExcel_3 = "Groupe_Client";

									row1.Groupe_Client = temp_row_tFileInputExcel_3[columnIndex_tFileInputExcel_3];
								} else {
									row1.Groupe_Client = null;
									emptyColumnCount_tFileInputExcel_3++;
								}
								columnIndex_tFileInputExcel_3 = 3;

								if (temp_row_tFileInputExcel_3[columnIndex_tFileInputExcel_3].length() > 0) {
									curColNum_tFileInputExcel_3 = columnIndex_tFileInputExcel_3
											+ start_column_tFileInputExcel_3 + 1;
									curColName_tFileInputExcel_3 = "Account_Manager";

									row1.Account_Manager = temp_row_tFileInputExcel_3[columnIndex_tFileInputExcel_3];
								} else {
									row1.Account_Manager = null;
									emptyColumnCount_tFileInputExcel_3++;
								}
								columnIndex_tFileInputExcel_3 = 4;

								if (temp_row_tFileInputExcel_3[columnIndex_tFileInputExcel_3].length() > 0) {
									curColNum_tFileInputExcel_3 = columnIndex_tFileInputExcel_3
											+ start_column_tFileInputExcel_3 + 1;
									curColName_tFileInputExcel_3 = "Equipe";

									row1.Equipe = temp_row_tFileInputExcel_3[columnIndex_tFileInputExcel_3];
								} else {
									row1.Equipe = null;
									emptyColumnCount_tFileInputExcel_3++;
								}
								columnIndex_tFileInputExcel_3 = 5;

								if (temp_row_tFileInputExcel_3[columnIndex_tFileInputExcel_3].length() > 0) {
									curColNum_tFileInputExcel_3 = columnIndex_tFileInputExcel_3
											+ start_column_tFileInputExcel_3 + 1;
									curColName_tFileInputExcel_3 = "Somme_de_Resiliation";

									row1.Somme_de_Resiliation = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_3[columnIndex_tFileInputExcel_3], null,
											'.' == decimalChar_tFileInputExcel_3 ? null
													: decimalChar_tFileInputExcel_3));
								} else {
									row1.Somme_de_Resiliation = null;
									emptyColumnCount_tFileInputExcel_3++;
								}
								columnIndex_tFileInputExcel_3 = 6;

								if (temp_row_tFileInputExcel_3[columnIndex_tFileInputExcel_3].length() > 0) {
									curColNum_tFileInputExcel_3 = columnIndex_tFileInputExcel_3
											+ start_column_tFileInputExcel_3 + 1;
									curColName_tFileInputExcel_3 = "Type_Resiliation";

									row1.Type_Resiliation = temp_row_tFileInputExcel_3[columnIndex_tFileInputExcel_3];
								} else {
									row1.Type_Resiliation = null;
									emptyColumnCount_tFileInputExcel_3++;
								}
								columnIndex_tFileInputExcel_3 = 7;

								if (temp_row_tFileInputExcel_3[columnIndex_tFileInputExcel_3].length() > 0) {
									curColNum_tFileInputExcel_3 = columnIndex_tFileInputExcel_3
											+ start_column_tFileInputExcel_3 + 1;
									curColName_tFileInputExcel_3 = "Motif";

									row1.Motif = temp_row_tFileInputExcel_3[columnIndex_tFileInputExcel_3];
								} else {
									row1.Motif = null;
									emptyColumnCount_tFileInputExcel_3++;
								}
								columnIndex_tFileInputExcel_3 = 8;

								if (temp_row_tFileInputExcel_3[columnIndex_tFileInputExcel_3].length() > 0) {
									curColNum_tFileInputExcel_3 = columnIndex_tFileInputExcel_3
											+ start_column_tFileInputExcel_3 + 1;
									curColName_tFileInputExcel_3 = "DFE";

									row1.DFE = temp_row_tFileInputExcel_3[columnIndex_tFileInputExcel_3];
								} else {
									row1.DFE = null;
									emptyColumnCount_tFileInputExcel_3++;
								}
								columnIndex_tFileInputExcel_3 = 9;

								if (temp_row_tFileInputExcel_3[columnIndex_tFileInputExcel_3].length() > 0) {
									curColNum_tFileInputExcel_3 = columnIndex_tFileInputExcel_3
											+ start_column_tFileInputExcel_3 + 1;
									curColName_tFileInputExcel_3 = "Engage_Oui_Non";

									row1.Engage_Oui_Non = temp_row_tFileInputExcel_3[columnIndex_tFileInputExcel_3];
								} else {
									row1.Engage_Oui_Non = null;
									emptyColumnCount_tFileInputExcel_3++;
								}
								columnIndex_tFileInputExcel_3 = 10;

								if (temp_row_tFileInputExcel_3[columnIndex_tFileInputExcel_3].length() > 0) {
									curColNum_tFileInputExcel_3 = columnIndex_tFileInputExcel_3
											+ start_column_tFileInputExcel_3 + 1;
									curColName_tFileInputExcel_3 = "Date_Depot";

									row1.Date_Depot = temp_row_tFileInputExcel_3[columnIndex_tFileInputExcel_3];
								} else {
									row1.Date_Depot = null;
									emptyColumnCount_tFileInputExcel_3++;
								}

								nb_line_tFileInputExcel_3++;

							} catch (java.lang.Exception e) {
								globalMap.put("tFileInputExcel_3_ERROR_MESSAGE", e.getMessage());
								whetherReject_tFileInputExcel_3 = true;
								System.err.println(e.getMessage());
								row1 = null;
							}

							/**
							 * [tFileInputExcel_3 begin ] stop
							 */

							/**
							 * [tFileInputExcel_3 main ] start
							 */

							currentComponent = "tFileInputExcel_3";

							tos_count_tFileInputExcel_3++;

							/**
							 * [tFileInputExcel_3 main ] stop
							 */

							/**
							 * [tFileInputExcel_3 process_data_begin ] start
							 */

							currentComponent = "tFileInputExcel_3";

							/**
							 * [tFileInputExcel_3 process_data_begin ] stop
							 */
// Start of branch "row1"
							if (row1 != null) {

								/**
								 * [tMap_5 main ] start
								 */

								currentComponent = "tMap_5";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "row1"

									);
								}

								boolean hasCasePrimitiveKeyWithNull_tMap_5 = false;

								// ###############################
								// # Input tables (lookups)
								boolean rejectedInnerJoin_tMap_5 = false;
								boolean mainRowRejected_tMap_5 = false;

								// ###############################
								{ // start of Var scope

									// ###############################
									// # Vars tables

									Var__tMap_5__Struct Var = Var__tMap_5;// ###############################
									// ###############################
									// # Output tables

									bbbb = null;

// # Output table : 'bbbb'
									bbbb_tmp.Type_Resiliation = row1.Type_Resiliation;
									bbbb = bbbb_tmp;
// ###############################

								} // end of Var scope

								rejectedInnerJoin_tMap_5 = false;

								tos_count_tMap_5++;

								/**
								 * [tMap_5 main ] stop
								 */

								/**
								 * [tMap_5 process_data_begin ] start
								 */

								currentComponent = "tMap_5";

								/**
								 * [tMap_5 process_data_begin ] stop
								 */
// Start of branch "bbbb"
								if (bbbb != null) {

									/**
									 * [tUnite_2 main ] start
									 */

									currentComponent = "tUnite_2";

									if (execStat) {
										runStat.updateStatOnConnection(iterateId, 1, 1

												, "bbbb"

										);
									}

//////////

// for output
									row3 = new row3Struct();

									row3.Type_Resiliation = bbbb.Type_Resiliation;

									nb_line_tUnite_2++;

//////////

									tos_count_tUnite_2++;

									/**
									 * [tUnite_2 main ] stop
									 */

									/**
									 * [tUnite_2 process_data_begin ] start
									 */

									currentComponent = "tUnite_2";

									/**
									 * [tUnite_2 process_data_begin ] stop
									 */

									/**
									 * [tMap_6 main ] start
									 */

									currentComponent = "tMap_6";

									if (execStat) {
										runStat.updateStatOnConnection(iterateId, 1, 1

												, "row3"

										);
									}

									boolean hasCasePrimitiveKeyWithNull_tMap_6 = false;

									// ###############################
									// # Input tables (lookups)
									boolean rejectedInnerJoin_tMap_6 = false;
									boolean mainRowRejected_tMap_6 = false;

									///////////////////////////////////////////////
									// Starting Lookup Table "row4"
									///////////////////////////////////////////////

									boolean forceLooprow4 = false;

									row4Struct row4ObjectFromLookup = null;

									if (!rejectedInnerJoin_tMap_6) { // G_TM_M_020

										hasCasePrimitiveKeyWithNull_tMap_6 = false;

										row4HashKey.Type_Resiliation = row3.Type_Resiliation;

										row4HashKey.hashCodeDirty = true;

										tHash_Lookup_row4.lookup(row4HashKey);

										if (!tHash_Lookup_row4.hasNext()) { // G_TM_M_090

											rejectedInnerJoin_tMap_6 = true;

										} // G_TM_M_090

									} // G_TM_M_020

									if (tHash_Lookup_row4 != null && tHash_Lookup_row4.getCount(row4HashKey) > 1) { // G
																													// 071

										// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row4'
										// and it contains more one result from keys : row4.Type_Resiliation = '" +
										// row4HashKey.Type_Resiliation + "'");
									} // G 071

									row4Struct row4 = null;

									row4Struct fromLookup_row4 = null;
									row4 = row4Default;

									if (tHash_Lookup_row4 != null && tHash_Lookup_row4.hasNext()) { // G 099

										fromLookup_row4 = tHash_Lookup_row4.next();

									} // G 099

									if (fromLookup_row4 != null) {
										row4 = fromLookup_row4;
									}

									// ###############################
									{ // start of Var scope

										// ###############################
										// # Vars tables

										Var__tMap_6__Struct Var = Var__tMap_6;// ###############################
										// ###############################
										// # Output tables

										llll = null;

										if (!rejectedInnerJoin_tMap_6) {
										} // closing inner join bracket (1)
// ###### START REJECTS ##### 

// # Output reject table : 'llll'
// # Filter conditions 
										if (rejectedInnerJoin_tMap_6) {
											llll_tmp.Type_Resiliation = row3.Type_Resiliation;
											llll = llll_tmp;
										} // closing filter/reject
// ###############################

									} // end of Var scope

									rejectedInnerJoin_tMap_6 = false;

									tos_count_tMap_6++;

									/**
									 * [tMap_6 main ] stop
									 */

									/**
									 * [tMap_6 process_data_begin ] start
									 */

									currentComponent = "tMap_6";

									/**
									 * [tMap_6 process_data_begin ] stop
									 */
// Start of branch "llll"
									if (llll != null) {

										/**
										 * [tDBOutput_2 main ] start
										 */

										currentComponent = "tDBOutput_2";

										if (execStat) {
											runStat.updateStatOnConnection(iterateId, 1, 1

													, "llll"

											);
										}

										whetherReject_tDBOutput_2 = false;
										if (llll.Type_Resiliation == null) {
											pstmt_tDBOutput_2.setNull(1, java.sql.Types.VARCHAR);
										} else {
											pstmt_tDBOutput_2.setString(1, llll.Type_Resiliation);
										}

										pstmt_tDBOutput_2.addBatch();
										nb_line_tDBOutput_2++;

										batchSizeCounter_tDBOutput_2++;

										////////// batch execute by batch size///////
										class LimitBytesHelper_tDBOutput_2 {
											public int limitBytePart1(int counter,
													java.sql.PreparedStatement pstmt_tDBOutput_2) throws Exception {
												try {

													for (int countEach_tDBOutput_2 : pstmt_tDBOutput_2.executeBatch()) {
														if (countEach_tDBOutput_2 == -2
																|| countEach_tDBOutput_2 == -3) {
															break;
														}
														counter += countEach_tDBOutput_2;
													}

												} catch (java.sql.BatchUpdateException e) {
													globalMap.put("tDBOutput_2_ERROR_MESSAGE", e.getMessage());

													int countSum_tDBOutput_2 = 0;
													for (int countEach_tDBOutput_2 : e.getUpdateCounts()) {
														counter += (countEach_tDBOutput_2 < 0 ? 0
																: countEach_tDBOutput_2);
													}

													System.err.println(e.getMessage());

												}
												return counter;
											}

											public int limitBytePart2(int counter,
													java.sql.PreparedStatement pstmt_tDBOutput_2) throws Exception {
												try {

													for (int countEach_tDBOutput_2 : pstmt_tDBOutput_2.executeBatch()) {
														if (countEach_tDBOutput_2 == -2
																|| countEach_tDBOutput_2 == -3) {
															break;
														}
														counter += countEach_tDBOutput_2;
													}

												} catch (java.sql.BatchUpdateException e) {
													globalMap.put("tDBOutput_2_ERROR_MESSAGE", e.getMessage());

													for (int countEach_tDBOutput_2 : e.getUpdateCounts()) {
														counter += (countEach_tDBOutput_2 < 0 ? 0
																: countEach_tDBOutput_2);
													}

													System.err.println(e.getMessage());

												}
												return counter;
											}
										}
										if ((batchSize_tDBOutput_2 > 0)
												&& (batchSize_tDBOutput_2 <= batchSizeCounter_tDBOutput_2)) {

											insertedCount_tDBOutput_2 = new LimitBytesHelper_tDBOutput_2()
													.limitBytePart1(insertedCount_tDBOutput_2, pstmt_tDBOutput_2);
											rowsToCommitCount_tDBOutput_2 = insertedCount_tDBOutput_2;

											batchSizeCounter_tDBOutput_2 = 0;
										}

										//////////// commit every////////////

										commitCounter_tDBOutput_2++;
										if (commitEvery_tDBOutput_2 <= commitCounter_tDBOutput_2) {
											if ((batchSize_tDBOutput_2 > 0) && (batchSizeCounter_tDBOutput_2 > 0)) {

												insertedCount_tDBOutput_2 = new LimitBytesHelper_tDBOutput_2()
														.limitBytePart1(insertedCount_tDBOutput_2, pstmt_tDBOutput_2);

												batchSizeCounter_tDBOutput_2 = 0;
											}
											if (rowsToCommitCount_tDBOutput_2 != 0) {

											}
											conn_tDBOutput_2.commit();
											if (rowsToCommitCount_tDBOutput_2 != 0) {

												rowsToCommitCount_tDBOutput_2 = 0;
											}
											commitCounter_tDBOutput_2 = 0;
										}

										tos_count_tDBOutput_2++;

										/**
										 * [tDBOutput_2 main ] stop
										 */

										/**
										 * [tDBOutput_2 process_data_begin ] start
										 */

										currentComponent = "tDBOutput_2";

										/**
										 * [tDBOutput_2 process_data_begin ] stop
										 */

										/**
										 * [tDBOutput_2 process_data_end ] start
										 */

										currentComponent = "tDBOutput_2";

										/**
										 * [tDBOutput_2 process_data_end ] stop
										 */

									} // End of branch "llll"

									/**
									 * [tMap_6 process_data_end ] start
									 */

									currentComponent = "tMap_6";

									/**
									 * [tMap_6 process_data_end ] stop
									 */

									/**
									 * [tUnite_2 process_data_end ] start
									 */

									currentComponent = "tUnite_2";

									/**
									 * [tUnite_2 process_data_end ] stop
									 */

								} // End of branch "bbbb"

								/**
								 * [tMap_5 process_data_end ] start
								 */

								currentComponent = "tMap_5";

								/**
								 * [tMap_5 process_data_end ] stop
								 */

							} // End of branch "row1"

							/**
							 * [tFileInputExcel_3 process_data_end ] start
							 */

							currentComponent = "tFileInputExcel_3";

							/**
							 * [tFileInputExcel_3 process_data_end ] stop
							 */

							/**
							 * [tFileInputExcel_3 end ] start
							 */

							currentComponent = "tFileInputExcel_3";

						}

						globalMap.put("tFileInputExcel_3_NB_LINE", nb_line_tFileInputExcel_3);

					}

				} finally {

					if (!(source_tFileInputExcel_3 instanceof java.io.InputStream)) {
						workbook_tFileInputExcel_3.getPackage().revert();
					}

				}

				ok_Hash.put("tFileInputExcel_3", true);
				end_Hash.put("tFileInputExcel_3", System.currentTimeMillis());

				/**
				 * [tFileInputExcel_3 end ] stop
				 */

				/**
				 * [tMap_5 end ] start
				 */

				currentComponent = "tMap_5";

// ###############################
// # Lookup hashes releasing
// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row1");
				}

				ok_Hash.put("tMap_5", true);
				end_Hash.put("tMap_5", System.currentTimeMillis());

				/**
				 * [tMap_5 end ] stop
				 */

				/**
				 * [tMap_4 begin ] start
				 */

				ok_Hash.put("tMap_4", false);
				start_Hash.put("tMap_4", System.currentTimeMillis());

				currentComponent = "tMap_4";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row2");
				}

				int tos_count_tMap_4 = 0;

// ###############################
// # Lookup's keys initialization
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_4__Struct {
				}
				Var__tMap_4__Struct Var__tMap_4 = new Var__tMap_4__Struct();
// ###############################

// ###############################
// # Outputs initialization
				aaaaStruct aaaa_tmp = new aaaaStruct();
// ###############################

				/**
				 * [tMap_4 begin ] stop
				 */

				/**
				 * [tFileInputExcel_4 begin ] start
				 */

				ok_Hash.put("tFileInputExcel_4", false);
				start_Hash.put("tFileInputExcel_4", System.currentTimeMillis());

				currentComponent = "tFileInputExcel_4";

				int tos_count_tFileInputExcel_4 = 0;

				final String decryptedPassword_tFileInputExcel_4 = routines.system.PasswordEncryptUtil
						.decryptPassword("enc:routine.encryption.key.v1:EbLo9AEXiJu7K7AfBsPi4pueP8d/I4L2DScG7A==");
				String password_tFileInputExcel_4 = decryptedPassword_tFileInputExcel_4;
				if (password_tFileInputExcel_4.isEmpty()) {
					password_tFileInputExcel_4 = null;
				}
				class RegexUtil_tFileInputExcel_4 {

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
				RegexUtil_tFileInputExcel_4 regexUtil_tFileInputExcel_4 = new RegexUtil_tFileInputExcel_4();

				Object source_tFileInputExcel_4 = "C:/Users/DELL/Downloads/parc facturé Mars 2022 (1).xlsx";
				org.apache.poi.xssf.usermodel.XSSFWorkbook workbook_tFileInputExcel_4 = null;

				if (source_tFileInputExcel_4 instanceof String) {
					workbook_tFileInputExcel_4 = (org.apache.poi.xssf.usermodel.XSSFWorkbook) org.apache.poi.ss.usermodel.WorkbookFactory
							.create(new java.io.File((String) source_tFileInputExcel_4), password_tFileInputExcel_4,
									true);
				} else if (source_tFileInputExcel_4 instanceof java.io.InputStream) {
					workbook_tFileInputExcel_4 = (org.apache.poi.xssf.usermodel.XSSFWorkbook) org.apache.poi.ss.usermodel.WorkbookFactory
							.create((java.io.InputStream) source_tFileInputExcel_4, password_tFileInputExcel_4);
				} else {
					workbook_tFileInputExcel_4 = null;
					throw new java.lang.Exception("The data source should be specified as Inputstream or File Path!");
				}
				try {

					java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> sheetList_tFileInputExcel_4 = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
					sheetList_tFileInputExcel_4.addAll(
							regexUtil_tFileInputExcel_4.getSheets(workbook_tFileInputExcel_4, "Suspension", false));
					if (sheetList_tFileInputExcel_4.size() <= 0) {
						throw new RuntimeException("Special sheets not exist!");
					}

					java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> sheetList_FilterNull_tFileInputExcel_4 = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
					for (org.apache.poi.xssf.usermodel.XSSFSheet sheet_FilterNull_tFileInputExcel_4 : sheetList_tFileInputExcel_4) {
						if (sheet_FilterNull_tFileInputExcel_4 != null
								&& sheetList_FilterNull_tFileInputExcel_4.iterator() != null
								&& sheet_FilterNull_tFileInputExcel_4.iterator().hasNext()) {
							sheetList_FilterNull_tFileInputExcel_4.add(sheet_FilterNull_tFileInputExcel_4);
						}
					}
					sheetList_tFileInputExcel_4 = sheetList_FilterNull_tFileInputExcel_4;
					if (sheetList_tFileInputExcel_4.size() > 0) {
						int nb_line_tFileInputExcel_4 = 0;

						int begin_line_tFileInputExcel_4 = 1;

						int footer_input_tFileInputExcel_4 = 0;

						int end_line_tFileInputExcel_4 = 0;
						for (org.apache.poi.xssf.usermodel.XSSFSheet sheet_tFileInputExcel_4 : sheetList_tFileInputExcel_4) {
							end_line_tFileInputExcel_4 += (sheet_tFileInputExcel_4.getLastRowNum() + 1);
						}
						end_line_tFileInputExcel_4 -= footer_input_tFileInputExcel_4;
						int limit_tFileInputExcel_4 = -1;
						int start_column_tFileInputExcel_4 = 1 - 1;
						int end_column_tFileInputExcel_4 = -1;

						org.apache.poi.xssf.usermodel.XSSFRow row_tFileInputExcel_4 = null;
						org.apache.poi.xssf.usermodel.XSSFSheet sheet_tFileInputExcel_4 = sheetList_tFileInputExcel_4
								.get(0);
						int rowCount_tFileInputExcel_4 = 0;
						int sheetIndex_tFileInputExcel_4 = 0;
						int currentRows_tFileInputExcel_4 = (sheetList_tFileInputExcel_4.get(0).getLastRowNum() + 1);

						// for the number format
						java.text.DecimalFormat df_tFileInputExcel_4 = new java.text.DecimalFormat(
								"#.####################################");
						char decimalChar_tFileInputExcel_4 = df_tFileInputExcel_4.getDecimalFormatSymbols()
								.getDecimalSeparator();

						for (int i_tFileInputExcel_4 = begin_line_tFileInputExcel_4; i_tFileInputExcel_4 < end_line_tFileInputExcel_4; i_tFileInputExcel_4++) {

							int emptyColumnCount_tFileInputExcel_4 = 0;

							if (limit_tFileInputExcel_4 != -1 && nb_line_tFileInputExcel_4 >= limit_tFileInputExcel_4) {
								break;
							}

							while (i_tFileInputExcel_4 >= rowCount_tFileInputExcel_4 + currentRows_tFileInputExcel_4) {
								rowCount_tFileInputExcel_4 += currentRows_tFileInputExcel_4;
								sheet_tFileInputExcel_4 = sheetList_tFileInputExcel_4
										.get(++sheetIndex_tFileInputExcel_4);
								currentRows_tFileInputExcel_4 = (sheet_tFileInputExcel_4.getLastRowNum() + 1);
							}
							globalMap.put("tFileInputExcel_4_CURRENT_SHEET", sheet_tFileInputExcel_4.getSheetName());
							if (rowCount_tFileInputExcel_4 <= i_tFileInputExcel_4) {
								row_tFileInputExcel_4 = sheet_tFileInputExcel_4
										.getRow(i_tFileInputExcel_4 - rowCount_tFileInputExcel_4);
							}
							row2 = null;
							int tempRowLength_tFileInputExcel_4 = 11;

							int columnIndex_tFileInputExcel_4 = 0;

							String[] temp_row_tFileInputExcel_4 = new String[tempRowLength_tFileInputExcel_4];
							int excel_end_column_tFileInputExcel_4;
							if (row_tFileInputExcel_4 == null) {
								excel_end_column_tFileInputExcel_4 = 0;
							} else {
								excel_end_column_tFileInputExcel_4 = row_tFileInputExcel_4.getLastCellNum();
							}
							int actual_end_column_tFileInputExcel_4;
							if (end_column_tFileInputExcel_4 == -1) {
								actual_end_column_tFileInputExcel_4 = excel_end_column_tFileInputExcel_4;
							} else {
								actual_end_column_tFileInputExcel_4 = end_column_tFileInputExcel_4 > excel_end_column_tFileInputExcel_4
										? excel_end_column_tFileInputExcel_4
										: end_column_tFileInputExcel_4;
							}
							org.apache.poi.ss.formula.eval.NumberEval ne_tFileInputExcel_4 = null;
							for (int i = 0; i < tempRowLength_tFileInputExcel_4; i++) {
								if (i + start_column_tFileInputExcel_4 < actual_end_column_tFileInputExcel_4) {
									org.apache.poi.ss.usermodel.Cell cell_tFileInputExcel_4 = row_tFileInputExcel_4
											.getCell(i + start_column_tFileInputExcel_4);
									if (cell_tFileInputExcel_4 != null) {
										switch (cell_tFileInputExcel_4.getCellType()) {
										case STRING:
											temp_row_tFileInputExcel_4[i] = cell_tFileInputExcel_4
													.getRichStringCellValue().getString();
											break;
										case NUMERIC:
											if (org.apache.poi.ss.usermodel.DateUtil
													.isCellDateFormatted(cell_tFileInputExcel_4)) {
												temp_row_tFileInputExcel_4[i] = cell_tFileInputExcel_4
														.getDateCellValue().toString();
											} else {
												temp_row_tFileInputExcel_4[i] = df_tFileInputExcel_4
														.format(cell_tFileInputExcel_4.getNumericCellValue());
											}
											break;
										case BOOLEAN:
											temp_row_tFileInputExcel_4[i] = String
													.valueOf(cell_tFileInputExcel_4.getBooleanCellValue());
											break;
										case FORMULA:
											switch (cell_tFileInputExcel_4.getCachedFormulaResultType()) {
											case STRING:
												temp_row_tFileInputExcel_4[i] = cell_tFileInputExcel_4
														.getRichStringCellValue().getString();
												break;
											case NUMERIC:
												if (org.apache.poi.ss.usermodel.DateUtil
														.isCellDateFormatted(cell_tFileInputExcel_4)) {
													temp_row_tFileInputExcel_4[i] = cell_tFileInputExcel_4
															.getDateCellValue().toString();
												} else {
													ne_tFileInputExcel_4 = new org.apache.poi.ss.formula.eval.NumberEval(
															cell_tFileInputExcel_4.getNumericCellValue());
													temp_row_tFileInputExcel_4[i] = ne_tFileInputExcel_4
															.getStringValue();
												}
												break;
											case BOOLEAN:
												temp_row_tFileInputExcel_4[i] = String
														.valueOf(cell_tFileInputExcel_4.getBooleanCellValue());
												break;
											default:
												temp_row_tFileInputExcel_4[i] = "";
											}
											break;
										default:
											temp_row_tFileInputExcel_4[i] = "";
										}
									} else {
										temp_row_tFileInputExcel_4[i] = "";
									}

								} else {
									temp_row_tFileInputExcel_4[i] = "";
								}
							}
							boolean whetherReject_tFileInputExcel_4 = false;
							row2 = new row2Struct();
							int curColNum_tFileInputExcel_4 = -1;
							String curColName_tFileInputExcel_4 = "";
							try {
								columnIndex_tFileInputExcel_4 = 0;

								if (temp_row_tFileInputExcel_4[columnIndex_tFileInputExcel_4].length() > 0) {
									curColNum_tFileInputExcel_4 = columnIndex_tFileInputExcel_4
											+ start_column_tFileInputExcel_4 + 1;
									curColName_tFileInputExcel_4 = "Custcode";

									row2.Custcode = ParserUtils.parseTo_Float(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_4[columnIndex_tFileInputExcel_4], null,
											'.' == decimalChar_tFileInputExcel_4 ? null
													: decimalChar_tFileInputExcel_4));
								} else {
									row2.Custcode = null;
									emptyColumnCount_tFileInputExcel_4++;
								}
								columnIndex_tFileInputExcel_4 = 1;

								if (temp_row_tFileInputExcel_4[columnIndex_tFileInputExcel_4].length() > 0) {
									curColNum_tFileInputExcel_4 = columnIndex_tFileInputExcel_4
											+ start_column_tFileInputExcel_4 + 1;
									curColName_tFileInputExcel_4 = "Raison_Sociale";

									row2.Raison_Sociale = temp_row_tFileInputExcel_4[columnIndex_tFileInputExcel_4];
								} else {
									row2.Raison_Sociale = null;
									emptyColumnCount_tFileInputExcel_4++;
								}
								columnIndex_tFileInputExcel_4 = 2;

								if (temp_row_tFileInputExcel_4[columnIndex_tFileInputExcel_4].length() > 0) {
									curColNum_tFileInputExcel_4 = columnIndex_tFileInputExcel_4
											+ start_column_tFileInputExcel_4 + 1;
									curColName_tFileInputExcel_4 = "Groupe_Client";

									row2.Groupe_Client = temp_row_tFileInputExcel_4[columnIndex_tFileInputExcel_4];
								} else {
									row2.Groupe_Client = null;
									emptyColumnCount_tFileInputExcel_4++;
								}
								columnIndex_tFileInputExcel_4 = 3;

								if (temp_row_tFileInputExcel_4[columnIndex_tFileInputExcel_4].length() > 0) {
									curColNum_tFileInputExcel_4 = columnIndex_tFileInputExcel_4
											+ start_column_tFileInputExcel_4 + 1;
									curColName_tFileInputExcel_4 = "Account_Manager";

									row2.Account_Manager = temp_row_tFileInputExcel_4[columnIndex_tFileInputExcel_4];
								} else {
									row2.Account_Manager = null;
									emptyColumnCount_tFileInputExcel_4++;
								}
								columnIndex_tFileInputExcel_4 = 4;

								if (temp_row_tFileInputExcel_4[columnIndex_tFileInputExcel_4].length() > 0) {
									curColNum_tFileInputExcel_4 = columnIndex_tFileInputExcel_4
											+ start_column_tFileInputExcel_4 + 1;
									curColName_tFileInputExcel_4 = "Equipe";

									row2.Equipe = temp_row_tFileInputExcel_4[columnIndex_tFileInputExcel_4];
								} else {
									row2.Equipe = null;
									emptyColumnCount_tFileInputExcel_4++;
								}
								columnIndex_tFileInputExcel_4 = 5;

								if (temp_row_tFileInputExcel_4[columnIndex_tFileInputExcel_4].length() > 0) {
									curColNum_tFileInputExcel_4 = columnIndex_tFileInputExcel_4
											+ start_column_tFileInputExcel_4 + 1;
									curColName_tFileInputExcel_4 = "Somme_de_Suspension";

									row2.Somme_de_Suspension = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_4[columnIndex_tFileInputExcel_4], null,
											'.' == decimalChar_tFileInputExcel_4 ? null
													: decimalChar_tFileInputExcel_4));
								} else {
									row2.Somme_de_Suspension = null;
									emptyColumnCount_tFileInputExcel_4++;
								}
								columnIndex_tFileInputExcel_4 = 6;

								if (temp_row_tFileInputExcel_4[columnIndex_tFileInputExcel_4].length() > 0) {
									curColNum_tFileInputExcel_4 = columnIndex_tFileInputExcel_4
											+ start_column_tFileInputExcel_4 + 1;
									curColName_tFileInputExcel_4 = "Type_de_suspension";

									row2.Type_de_suspension = temp_row_tFileInputExcel_4[columnIndex_tFileInputExcel_4];
								} else {
									row2.Type_de_suspension = null;
									emptyColumnCount_tFileInputExcel_4++;
								}
								columnIndex_tFileInputExcel_4 = 7;

								if (temp_row_tFileInputExcel_4[columnIndex_tFileInputExcel_4].length() > 0) {
									curColNum_tFileInputExcel_4 = columnIndex_tFileInputExcel_4
											+ start_column_tFileInputExcel_4 + 1;
									curColName_tFileInputExcel_4 = "Motif";

									row2.Motif = temp_row_tFileInputExcel_4[columnIndex_tFileInputExcel_4];
								} else {
									row2.Motif = null;
									emptyColumnCount_tFileInputExcel_4++;
								}
								columnIndex_tFileInputExcel_4 = 8;

								if (temp_row_tFileInputExcel_4[columnIndex_tFileInputExcel_4].length() > 0) {
									curColNum_tFileInputExcel_4 = columnIndex_tFileInputExcel_4
											+ start_column_tFileInputExcel_4 + 1;
									curColName_tFileInputExcel_4 = "DFE";

									row2.DFE = temp_row_tFileInputExcel_4[columnIndex_tFileInputExcel_4];
								} else {
									row2.DFE = null;
									emptyColumnCount_tFileInputExcel_4++;
								}
								columnIndex_tFileInputExcel_4 = 9;

								if (temp_row_tFileInputExcel_4[columnIndex_tFileInputExcel_4].length() > 0) {
									curColNum_tFileInputExcel_4 = columnIndex_tFileInputExcel_4
											+ start_column_tFileInputExcel_4 + 1;
									curColName_tFileInputExcel_4 = "Engage_Oui_Non";

									row2.Engage_Oui_Non = temp_row_tFileInputExcel_4[columnIndex_tFileInputExcel_4];
								} else {
									row2.Engage_Oui_Non = null;
									emptyColumnCount_tFileInputExcel_4++;
								}
								columnIndex_tFileInputExcel_4 = 10;

								if (temp_row_tFileInputExcel_4[columnIndex_tFileInputExcel_4].length() > 0) {
									curColNum_tFileInputExcel_4 = columnIndex_tFileInputExcel_4
											+ start_column_tFileInputExcel_4 + 1;
									curColName_tFileInputExcel_4 = "Date_Depot";

									row2.Date_Depot = temp_row_tFileInputExcel_4[columnIndex_tFileInputExcel_4];
								} else {
									row2.Date_Depot = null;
									emptyColumnCount_tFileInputExcel_4++;
								}

								nb_line_tFileInputExcel_4++;

							} catch (java.lang.Exception e) {
								globalMap.put("tFileInputExcel_4_ERROR_MESSAGE", e.getMessage());
								whetherReject_tFileInputExcel_4 = true;
								System.err.println(e.getMessage());
								row2 = null;
							}

							/**
							 * [tFileInputExcel_4 begin ] stop
							 */

							/**
							 * [tFileInputExcel_4 main ] start
							 */

							currentComponent = "tFileInputExcel_4";

							tos_count_tFileInputExcel_4++;

							/**
							 * [tFileInputExcel_4 main ] stop
							 */

							/**
							 * [tFileInputExcel_4 process_data_begin ] start
							 */

							currentComponent = "tFileInputExcel_4";

							/**
							 * [tFileInputExcel_4 process_data_begin ] stop
							 */
// Start of branch "row2"
							if (row2 != null) {

								/**
								 * [tMap_4 main ] start
								 */

								currentComponent = "tMap_4";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "row2"

									);
								}

								boolean hasCasePrimitiveKeyWithNull_tMap_4 = false;

								// ###############################
								// # Input tables (lookups)
								boolean rejectedInnerJoin_tMap_4 = false;
								boolean mainRowRejected_tMap_4 = false;

								// ###############################
								{ // start of Var scope

									// ###############################
									// # Vars tables

									Var__tMap_4__Struct Var = Var__tMap_4;// ###############################
									// ###############################
									// # Output tables

									aaaa = null;

// # Output table : 'aaaa'
									aaaa_tmp.Type_de_suspension = row2.Type_de_suspension;
									aaaa = aaaa_tmp;
// ###############################

								} // end of Var scope

								rejectedInnerJoin_tMap_4 = false;

								tos_count_tMap_4++;

								/**
								 * [tMap_4 main ] stop
								 */

								/**
								 * [tMap_4 process_data_begin ] start
								 */

								currentComponent = "tMap_4";

								/**
								 * [tMap_4 process_data_begin ] stop
								 */
// Start of branch "aaaa"
								if (aaaa != null) {

									/**
									 * [tUnite_2 main ] start
									 */

									currentComponent = "tUnite_2";

									if (execStat) {
										runStat.updateStatOnConnection(iterateId, 1, 1

												, "aaaa"

										);
									}

//////////

// for output
									row3 = new row3Struct();

									row3.Type_Resiliation = aaaa.Type_de_suspension;

									nb_line_tUnite_2++;

//////////

									tos_count_tUnite_2++;

									/**
									 * [tUnite_2 main ] stop
									 */

									/**
									 * [tUnite_2 process_data_begin ] start
									 */

									currentComponent = "tUnite_2";

									/**
									 * [tUnite_2 process_data_begin ] stop
									 */

									/**
									 * [tMap_6 main ] start
									 */

									currentComponent = "tMap_6";

									if (execStat) {
										runStat.updateStatOnConnection(iterateId, 1, 1

												, "row3"

										);
									}

									boolean hasCasePrimitiveKeyWithNull_tMap_6 = false;

									// ###############################
									// # Input tables (lookups)
									boolean rejectedInnerJoin_tMap_6 = false;
									boolean mainRowRejected_tMap_6 = false;

									///////////////////////////////////////////////
									// Starting Lookup Table "row4"
									///////////////////////////////////////////////

									boolean forceLooprow4 = false;

									row4Struct row4ObjectFromLookup = null;

									if (!rejectedInnerJoin_tMap_6) { // G_TM_M_020

										hasCasePrimitiveKeyWithNull_tMap_6 = false;

										row4HashKey.Type_Resiliation = row3.Type_Resiliation;

										row4HashKey.hashCodeDirty = true;

										tHash_Lookup_row4.lookup(row4HashKey);

										if (!tHash_Lookup_row4.hasNext()) { // G_TM_M_090

											rejectedInnerJoin_tMap_6 = true;

										} // G_TM_M_090

									} // G_TM_M_020

									if (tHash_Lookup_row4 != null && tHash_Lookup_row4.getCount(row4HashKey) > 1) { // G
																													// 071

										// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row4'
										// and it contains more one result from keys : row4.Type_Resiliation = '" +
										// row4HashKey.Type_Resiliation + "'");
									} // G 071

									row4Struct row4 = null;

									row4Struct fromLookup_row4 = null;
									row4 = row4Default;

									if (tHash_Lookup_row4 != null && tHash_Lookup_row4.hasNext()) { // G 099

										fromLookup_row4 = tHash_Lookup_row4.next();

									} // G 099

									if (fromLookup_row4 != null) {
										row4 = fromLookup_row4;
									}

									// ###############################
									{ // start of Var scope

										// ###############################
										// # Vars tables

										Var__tMap_6__Struct Var = Var__tMap_6;// ###############################
										// ###############################
										// # Output tables

										llll = null;

										if (!rejectedInnerJoin_tMap_6) {
										} // closing inner join bracket (1)
// ###### START REJECTS ##### 

// # Output reject table : 'llll'
// # Filter conditions 
										if (rejectedInnerJoin_tMap_6) {
											llll_tmp.Type_Resiliation = row3.Type_Resiliation;
											llll = llll_tmp;
										} // closing filter/reject
// ###############################

									} // end of Var scope

									rejectedInnerJoin_tMap_6 = false;

									tos_count_tMap_6++;

									/**
									 * [tMap_6 main ] stop
									 */

									/**
									 * [tMap_6 process_data_begin ] start
									 */

									currentComponent = "tMap_6";

									/**
									 * [tMap_6 process_data_begin ] stop
									 */
// Start of branch "llll"
									if (llll != null) {

										/**
										 * [tDBOutput_2 main ] start
										 */

										currentComponent = "tDBOutput_2";

										if (execStat) {
											runStat.updateStatOnConnection(iterateId, 1, 1

													, "llll"

											);
										}

										whetherReject_tDBOutput_2 = false;
										if (llll.Type_Resiliation == null) {
											pstmt_tDBOutput_2.setNull(1, java.sql.Types.VARCHAR);
										} else {
											pstmt_tDBOutput_2.setString(1, llll.Type_Resiliation);
										}

										pstmt_tDBOutput_2.addBatch();
										nb_line_tDBOutput_2++;

										batchSizeCounter_tDBOutput_2++;

										////////// batch execute by batch size///////
										class LimitBytesHelper_tDBOutput_2 {
											public int limitBytePart1(int counter,
													java.sql.PreparedStatement pstmt_tDBOutput_2) throws Exception {
												try {

													for (int countEach_tDBOutput_2 : pstmt_tDBOutput_2.executeBatch()) {
														if (countEach_tDBOutput_2 == -2
																|| countEach_tDBOutput_2 == -3) {
															break;
														}
														counter += countEach_tDBOutput_2;
													}

												} catch (java.sql.BatchUpdateException e) {
													globalMap.put("tDBOutput_2_ERROR_MESSAGE", e.getMessage());

													int countSum_tDBOutput_2 = 0;
													for (int countEach_tDBOutput_2 : e.getUpdateCounts()) {
														counter += (countEach_tDBOutput_2 < 0 ? 0
																: countEach_tDBOutput_2);
													}

													System.err.println(e.getMessage());

												}
												return counter;
											}

											public int limitBytePart2(int counter,
													java.sql.PreparedStatement pstmt_tDBOutput_2) throws Exception {
												try {

													for (int countEach_tDBOutput_2 : pstmt_tDBOutput_2.executeBatch()) {
														if (countEach_tDBOutput_2 == -2
																|| countEach_tDBOutput_2 == -3) {
															break;
														}
														counter += countEach_tDBOutput_2;
													}

												} catch (java.sql.BatchUpdateException e) {
													globalMap.put("tDBOutput_2_ERROR_MESSAGE", e.getMessage());

													for (int countEach_tDBOutput_2 : e.getUpdateCounts()) {
														counter += (countEach_tDBOutput_2 < 0 ? 0
																: countEach_tDBOutput_2);
													}

													System.err.println(e.getMessage());

												}
												return counter;
											}
										}
										if ((batchSize_tDBOutput_2 > 0)
												&& (batchSize_tDBOutput_2 <= batchSizeCounter_tDBOutput_2)) {

											insertedCount_tDBOutput_2 = new LimitBytesHelper_tDBOutput_2()
													.limitBytePart1(insertedCount_tDBOutput_2, pstmt_tDBOutput_2);
											rowsToCommitCount_tDBOutput_2 = insertedCount_tDBOutput_2;

											batchSizeCounter_tDBOutput_2 = 0;
										}

										//////////// commit every////////////

										commitCounter_tDBOutput_2++;
										if (commitEvery_tDBOutput_2 <= commitCounter_tDBOutput_2) {
											if ((batchSize_tDBOutput_2 > 0) && (batchSizeCounter_tDBOutput_2 > 0)) {

												insertedCount_tDBOutput_2 = new LimitBytesHelper_tDBOutput_2()
														.limitBytePart1(insertedCount_tDBOutput_2, pstmt_tDBOutput_2);

												batchSizeCounter_tDBOutput_2 = 0;
											}
											if (rowsToCommitCount_tDBOutput_2 != 0) {

											}
											conn_tDBOutput_2.commit();
											if (rowsToCommitCount_tDBOutput_2 != 0) {

												rowsToCommitCount_tDBOutput_2 = 0;
											}
											commitCounter_tDBOutput_2 = 0;
										}

										tos_count_tDBOutput_2++;

										/**
										 * [tDBOutput_2 main ] stop
										 */

										/**
										 * [tDBOutput_2 process_data_begin ] start
										 */

										currentComponent = "tDBOutput_2";

										/**
										 * [tDBOutput_2 process_data_begin ] stop
										 */

										/**
										 * [tDBOutput_2 process_data_end ] start
										 */

										currentComponent = "tDBOutput_2";

										/**
										 * [tDBOutput_2 process_data_end ] stop
										 */

									} // End of branch "llll"

									/**
									 * [tMap_6 process_data_end ] start
									 */

									currentComponent = "tMap_6";

									/**
									 * [tMap_6 process_data_end ] stop
									 */

									/**
									 * [tUnite_2 process_data_end ] start
									 */

									currentComponent = "tUnite_2";

									/**
									 * [tUnite_2 process_data_end ] stop
									 */

								} // End of branch "aaaa"

								/**
								 * [tMap_4 process_data_end ] start
								 */

								currentComponent = "tMap_4";

								/**
								 * [tMap_4 process_data_end ] stop
								 */

							} // End of branch "row2"

							/**
							 * [tFileInputExcel_4 process_data_end ] start
							 */

							currentComponent = "tFileInputExcel_4";

							/**
							 * [tFileInputExcel_4 process_data_end ] stop
							 */

							/**
							 * [tFileInputExcel_4 end ] start
							 */

							currentComponent = "tFileInputExcel_4";

						}

						globalMap.put("tFileInputExcel_4_NB_LINE", nb_line_tFileInputExcel_4);

					}

				} finally {

					if (!(source_tFileInputExcel_4 instanceof java.io.InputStream)) {
						workbook_tFileInputExcel_4.getPackage().revert();
					}

				}

				ok_Hash.put("tFileInputExcel_4", true);
				end_Hash.put("tFileInputExcel_4", System.currentTimeMillis());

				/**
				 * [tFileInputExcel_4 end ] stop
				 */

				/**
				 * [tMap_4 end ] start
				 */

				currentComponent = "tMap_4";

// ###############################
// # Lookup hashes releasing
// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row2");
				}

				ok_Hash.put("tMap_4", true);
				end_Hash.put("tMap_4", System.currentTimeMillis());

				/**
				 * [tMap_4 end ] stop
				 */

				/**
				 * [tUnite_2 end ] start
				 */

				currentComponent = "tUnite_2";

				globalMap.put("tUnite_2_NB_LINE", nb_line_tUnite_2);
				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "bbbb", "aaaa");
				}

				ok_Hash.put("tUnite_2", true);
				end_Hash.put("tUnite_2", System.currentTimeMillis());

				/**
				 * [tUnite_2 end ] stop
				 */

				/**
				 * [tMap_6 end ] start
				 */

				currentComponent = "tMap_6";

// ###############################
// # Lookup hashes releasing
				if (tHash_Lookup_row4 != null) {
					tHash_Lookup_row4.endGet();
				}
				globalMap.remove("tHash_Lookup_row4");

// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row3");
				}

				ok_Hash.put("tMap_6", true);
				end_Hash.put("tMap_6", System.currentTimeMillis());

				/**
				 * [tMap_6 end ] stop
				 */

				/**
				 * [tDBOutput_2 end ] start
				 */

				currentComponent = "tDBOutput_2";

				try {
					int countSum_tDBOutput_2 = 0;
					if (pstmt_tDBOutput_2 != null && batchSizeCounter_tDBOutput_2 > 0) {

						for (int countEach_tDBOutput_2 : pstmt_tDBOutput_2.executeBatch()) {
							if (countEach_tDBOutput_2 == -2 || countEach_tDBOutput_2 == -3) {
								break;
							}
							countSum_tDBOutput_2 += countEach_tDBOutput_2;
						}
						rowsToCommitCount_tDBOutput_2 += countSum_tDBOutput_2;

					}

					insertedCount_tDBOutput_2 += countSum_tDBOutput_2;

				} catch (java.sql.BatchUpdateException e) {
					globalMap.put("tDBOutput_2_ERROR_MESSAGE", e.getMessage());

					int countSum_tDBOutput_2 = 0;
					for (int countEach_tDBOutput_2 : e.getUpdateCounts()) {
						countSum_tDBOutput_2 += (countEach_tDBOutput_2 < 0 ? 0 : countEach_tDBOutput_2);
					}
					rowsToCommitCount_tDBOutput_2 += countSum_tDBOutput_2;

					insertedCount_tDBOutput_2 += countSum_tDBOutput_2;

					System.err.println(e.getMessage());

				}
				if (pstmt_tDBOutput_2 != null) {

					pstmt_tDBOutput_2.close();
					resourceMap.remove("pstmt_tDBOutput_2");

				}
				resourceMap.put("statementClosed_tDBOutput_2", true);
				if (rowsToCommitCount_tDBOutput_2 != 0) {

				}
				conn_tDBOutput_2.commit();
				if (rowsToCommitCount_tDBOutput_2 != 0) {

					rowsToCommitCount_tDBOutput_2 = 0;
				}
				commitCounter_tDBOutput_2 = 0;
				conn_tDBOutput_2.close();
				resourceMap.put("finish_tDBOutput_2", true);

				nb_line_deleted_tDBOutput_2 = nb_line_deleted_tDBOutput_2 + deletedCount_tDBOutput_2;
				nb_line_update_tDBOutput_2 = nb_line_update_tDBOutput_2 + updatedCount_tDBOutput_2;
				nb_line_inserted_tDBOutput_2 = nb_line_inserted_tDBOutput_2 + insertedCount_tDBOutput_2;
				nb_line_rejected_tDBOutput_2 = nb_line_rejected_tDBOutput_2 + rejectedCount_tDBOutput_2;

				globalMap.put("tDBOutput_2_NB_LINE", nb_line_tDBOutput_2);
				globalMap.put("tDBOutput_2_NB_LINE_UPDATED", nb_line_update_tDBOutput_2);
				globalMap.put("tDBOutput_2_NB_LINE_INSERTED", nb_line_inserted_tDBOutput_2);
				globalMap.put("tDBOutput_2_NB_LINE_DELETED", nb_line_deleted_tDBOutput_2);
				globalMap.put("tDBOutput_2_NB_LINE_REJECTED", nb_line_rejected_tDBOutput_2);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "llll");
				}

				ok_Hash.put("tDBOutput_2", true);
				end_Hash.put("tDBOutput_2", System.currentTimeMillis());

				/**
				 * [tDBOutput_2 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			// free memory for "tMap_6"
			globalMap.remove("tHash_Lookup_row4");

			try {

				/**
				 * [tFileInputExcel_3 finally ] start
				 */

				currentComponent = "tFileInputExcel_3";

				/**
				 * [tFileInputExcel_3 finally ] stop
				 */

				/**
				 * [tMap_5 finally ] start
				 */

				currentComponent = "tMap_5";

				/**
				 * [tMap_5 finally ] stop
				 */

				/**
				 * [tFileInputExcel_4 finally ] start
				 */

				currentComponent = "tFileInputExcel_4";

				/**
				 * [tFileInputExcel_4 finally ] stop
				 */

				/**
				 * [tMap_4 finally ] start
				 */

				currentComponent = "tMap_4";

				/**
				 * [tMap_4 finally ] stop
				 */

				/**
				 * [tUnite_2 finally ] start
				 */

				currentComponent = "tUnite_2";

				/**
				 * [tUnite_2 finally ] stop
				 */

				/**
				 * [tMap_6 finally ] start
				 */

				currentComponent = "tMap_6";

				/**
				 * [tMap_6 finally ] stop
				 */

				/**
				 * [tDBOutput_2 finally ] start
				 */

				currentComponent = "tDBOutput_2";

				try {
					if (resourceMap.get("statementClosed_tDBOutput_2") == null) {
						java.sql.PreparedStatement pstmtToClose_tDBOutput_2 = null;
						if ((pstmtToClose_tDBOutput_2 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmt_tDBOutput_2")) != null) {
							pstmtToClose_tDBOutput_2.close();
						}
					}
				} finally {
					if (resourceMap.get("finish_tDBOutput_2") == null) {
						java.sql.Connection ctn_tDBOutput_2 = null;
						if ((ctn_tDBOutput_2 = (java.sql.Connection) resourceMap.get("conn_tDBOutput_2")) != null) {
							try {
								ctn_tDBOutput_2.close();
							} catch (java.sql.SQLException sqlEx_tDBOutput_2) {
								String errorMessage_tDBOutput_2 = "failed to close the connection in tDBOutput_2 :"
										+ sqlEx_tDBOutput_2.getMessage();
								System.err.println(errorMessage_tDBOutput_2);
							}
						}
					}
				}

				/**
				 * [tDBOutput_2 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputExcel_3_SUBPROCESS_STATE", 1);
	}

	public void tLogRow_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tLogRow_1_SUBPROCESS_STATE", 0);

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

				/**
				 * [tLogRow_1 begin ] start
				 */

				ok_Hash.put("tLogRow_1", false);
				start_Hash.put("tLogRow_1", System.currentTimeMillis());

				currentComponent = "tLogRow_1";

				int tos_count_tLogRow_1 = 0;

				///////////////////////

				final String OUTPUT_FIELD_SEPARATOR_tLogRow_1 = "|";
				java.io.PrintStream consoleOut_tLogRow_1 = null;

				StringBuilder strBuffer_tLogRow_1 = null;
				int nb_line_tLogRow_1 = 0;
///////////////////////    			

				/**
				 * [tLogRow_1 begin ] stop
				 */

				/**
				 * [tLogRow_1 main ] start
				 */

				currentComponent = "tLogRow_1";

				tos_count_tLogRow_1++;

				/**
				 * [tLogRow_1 main ] stop
				 */

				/**
				 * [tLogRow_1 process_data_begin ] start
				 */

				currentComponent = "tLogRow_1";

				/**
				 * [tLogRow_1 process_data_begin ] stop
				 */

				/**
				 * [tLogRow_1 process_data_end ] start
				 */

				currentComponent = "tLogRow_1";

				/**
				 * [tLogRow_1 process_data_end ] stop
				 */

				/**
				 * [tLogRow_1 end ] start
				 */

				currentComponent = "tLogRow_1";

//////
//////
				globalMap.put("tLogRow_1_NB_LINE", nb_line_tLogRow_1);

///////////////////////    			

				ok_Hash.put("tLogRow_1", true);
				end_Hash.put("tLogRow_1", System.currentTimeMillis());

				/**
				 * [tLogRow_1 end ] stop
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
				 * [tLogRow_1 finally ] start
				 */

				currentComponent = "tLogRow_1";

				/**
				 * [tLogRow_1 finally ] stop
				 */
			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tLogRow_1_SUBPROCESS_STATE", 1);
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
		final test_ test_Class = new test_();

		int exitCode = test_Class.runJobInTOS(args);

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
			java.io.InputStream inContext = test_.class.getClassLoader()
					.getResourceAsStream("orange/test__0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = test_.class.getClassLoader()
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
			tFileInputExcel_3Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tFileInputExcel_3) {
			globalMap.put("tFileInputExcel_3_SUBPROCESS_STATE", -1);

			e_tFileInputExcel_3.printStackTrace();

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if (false) {
			System.out.println((endUsedMemory - startUsedMemory) + " bytes memory increase when running : test_");
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
 * 170048 characters generated by Talend Open Studio for Data Integration on the
 * 6 mai 2022 à 21:27:21 CEST
 ************************************************************************************************/