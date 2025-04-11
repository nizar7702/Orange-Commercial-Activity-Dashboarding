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

package orange.time_0_1;

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
 * Job: Time Purpose: <br>
 * Description: <br>
 * 
 * @author user@talend.com
 * @version 8.0.1.20211109_1610
 * @status
 */
public class Time implements TalendJob {

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
	private final String jobName = "Time";
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
					Time.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(Time.this, new Object[] { e, currentComponent, globalMap });
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

	public void tDBInput_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBOutput_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public static class zzzStruct implements routines.system.IPersistableRow<zzzStruct> {
		final static byte[] commonByteArrayLock_ORANGE_Time = new byte[0];
		static byte[] commonByteArray_ORANGE_Time = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public java.util.Date PK_Date;

		public java.util.Date getPK_Date() {
			return this.PK_Date;
		}

		public String Date_Name;

		public String getDate_Name() {
			return this.Date_Name;
		}

		public java.util.Date Year;

		public java.util.Date getYear() {
			return this.Year;
		}

		public String Year_Name;

		public String getYear_Name() {
			return this.Year_Name;
		}

		public java.util.Date Half_Year;

		public java.util.Date getHalf_Year() {
			return this.Half_Year;
		}

		public String Half_Year_Name;

		public String getHalf_Year_Name() {
			return this.Half_Year_Name;
		}

		public java.util.Date Quarter;

		public java.util.Date getQuarter() {
			return this.Quarter;
		}

		public String Quarter_Name;

		public String getQuarter_Name() {
			return this.Quarter_Name;
		}

		public java.util.Date Trimester;

		public java.util.Date getTrimester() {
			return this.Trimester;
		}

		public String Trimester_Name;

		public String getTrimester_Name() {
			return this.Trimester_Name;
		}

		public java.util.Date Month;

		public java.util.Date getMonth() {
			return this.Month;
		}

		public String Month_Name;

		public String getMonth_Name() {
			return this.Month_Name;
		}

		public Integer Day_Of_Year;

		public Integer getDay_Of_Year() {
			return this.Day_Of_Year;
		}

		public String Day_Of_Year_Name;

		public String getDay_Of_Year_Name() {
			return this.Day_Of_Year_Name;
		}

		public Integer Day_Of_Half_Year;

		public Integer getDay_Of_Half_Year() {
			return this.Day_Of_Half_Year;
		}

		public String Day_Of_Half_Year_Name;

		public String getDay_Of_Half_Year_Name() {
			return this.Day_Of_Half_Year_Name;
		}

		public Integer Day_Of_Trimester;

		public Integer getDay_Of_Trimester() {
			return this.Day_Of_Trimester;
		}

		public String Day_Of_Trimester_Name;

		public String getDay_Of_Trimester_Name() {
			return this.Day_Of_Trimester_Name;
		}

		public Integer Day_Of_Quarter;

		public Integer getDay_Of_Quarter() {
			return this.Day_Of_Quarter;
		}

		public String Day_Of_Quarter_Name;

		public String getDay_Of_Quarter_Name() {
			return this.Day_Of_Quarter_Name;
		}

		public Integer Day_Of_Month;

		public Integer getDay_Of_Month() {
			return this.Day_Of_Month;
		}

		public String Day_Of_Month_Name;

		public String getDay_Of_Month_Name() {
			return this.Day_Of_Month_Name;
		}

		public Integer Month_Of_Year;

		public Integer getMonth_Of_Year() {
			return this.Month_Of_Year;
		}

		public String Month_Of_Year_Name;

		public String getMonth_Of_Year_Name() {
			return this.Month_Of_Year_Name;
		}

		public Integer Month_Of_Half_Year;

		public Integer getMonth_Of_Half_Year() {
			return this.Month_Of_Half_Year;
		}

		public String Month_Of_Half_Year_Name;

		public String getMonth_Of_Half_Year_Name() {
			return this.Month_Of_Half_Year_Name;
		}

		public Integer Month_Of_Trimester;

		public Integer getMonth_Of_Trimester() {
			return this.Month_Of_Trimester;
		}

		public String Month_Of_Trimester_Name;

		public String getMonth_Of_Trimester_Name() {
			return this.Month_Of_Trimester_Name;
		}

		public Integer Month_Of_Quarter;

		public Integer getMonth_Of_Quarter() {
			return this.Month_Of_Quarter;
		}

		public String Month_Of_Quarter_Name;

		public String getMonth_Of_Quarter_Name() {
			return this.Month_Of_Quarter_Name;
		}

		public Integer Quarter_Of_Year;

		public Integer getQuarter_Of_Year() {
			return this.Quarter_Of_Year;
		}

		public String Quarter_Of_Year_Name;

		public String getQuarter_Of_Year_Name() {
			return this.Quarter_Of_Year_Name;
		}

		public Integer Quarter_Of_Half_Year;

		public Integer getQuarter_Of_Half_Year() {
			return this.Quarter_Of_Half_Year;
		}

		public String Quarter_Of_Half_Year_Name;

		public String getQuarter_Of_Half_Year_Name() {
			return this.Quarter_Of_Half_Year_Name;
		}

		public Integer Trimester_Of_Year;

		public Integer getTrimester_Of_Year() {
			return this.Trimester_Of_Year;
		}

		public String Trimester_Of_Year_Name;

		public String getTrimester_Of_Year_Name() {
			return this.Trimester_Of_Year_Name;
		}

		public Integer Half_Year_Of_Year;

		public Integer getHalf_Year_Of_Year() {
			return this.Half_Year_Of_Year;
		}

		public String Half_Year_Of_Year_Name;

		public String getHalf_Year_Of_Year_Name() {
			return this.Half_Year_Of_Year_Name;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.PK_Date == null) ? 0 : this.PK_Date.hashCode());

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
			final zzzStruct other = (zzzStruct) obj;

			if (this.PK_Date == null) {
				if (other.PK_Date != null)
					return false;

			} else if (!this.PK_Date.equals(other.PK_Date))

				return false;

			return true;
		}

		public void copyDataTo(zzzStruct other) {

			other.PK_Date = this.PK_Date;
			other.Date_Name = this.Date_Name;
			other.Year = this.Year;
			other.Year_Name = this.Year_Name;
			other.Half_Year = this.Half_Year;
			other.Half_Year_Name = this.Half_Year_Name;
			other.Quarter = this.Quarter;
			other.Quarter_Name = this.Quarter_Name;
			other.Trimester = this.Trimester;
			other.Trimester_Name = this.Trimester_Name;
			other.Month = this.Month;
			other.Month_Name = this.Month_Name;
			other.Day_Of_Year = this.Day_Of_Year;
			other.Day_Of_Year_Name = this.Day_Of_Year_Name;
			other.Day_Of_Half_Year = this.Day_Of_Half_Year;
			other.Day_Of_Half_Year_Name = this.Day_Of_Half_Year_Name;
			other.Day_Of_Trimester = this.Day_Of_Trimester;
			other.Day_Of_Trimester_Name = this.Day_Of_Trimester_Name;
			other.Day_Of_Quarter = this.Day_Of_Quarter;
			other.Day_Of_Quarter_Name = this.Day_Of_Quarter_Name;
			other.Day_Of_Month = this.Day_Of_Month;
			other.Day_Of_Month_Name = this.Day_Of_Month_Name;
			other.Month_Of_Year = this.Month_Of_Year;
			other.Month_Of_Year_Name = this.Month_Of_Year_Name;
			other.Month_Of_Half_Year = this.Month_Of_Half_Year;
			other.Month_Of_Half_Year_Name = this.Month_Of_Half_Year_Name;
			other.Month_Of_Trimester = this.Month_Of_Trimester;
			other.Month_Of_Trimester_Name = this.Month_Of_Trimester_Name;
			other.Month_Of_Quarter = this.Month_Of_Quarter;
			other.Month_Of_Quarter_Name = this.Month_Of_Quarter_Name;
			other.Quarter_Of_Year = this.Quarter_Of_Year;
			other.Quarter_Of_Year_Name = this.Quarter_Of_Year_Name;
			other.Quarter_Of_Half_Year = this.Quarter_Of_Half_Year;
			other.Quarter_Of_Half_Year_Name = this.Quarter_Of_Half_Year_Name;
			other.Trimester_Of_Year = this.Trimester_Of_Year;
			other.Trimester_Of_Year_Name = this.Trimester_Of_Year_Name;
			other.Half_Year_Of_Year = this.Half_Year_Of_Year;
			other.Half_Year_Of_Year_Name = this.Half_Year_Of_Year_Name;

		}

		public void copyKeysDataTo(zzzStruct other) {

			other.PK_Date = this.PK_Date;

		}

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_Time.length) {
					if (length < 1024 && commonByteArray_ORANGE_Time.length == 0) {
						commonByteArray_ORANGE_Time = new byte[1024];
					} else {
						commonByteArray_ORANGE_Time = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_Time, 0, length);
				strReturn = new String(commonByteArray_ORANGE_Time, 0, length, utf8Charset);
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
				if (length > commonByteArray_ORANGE_Time.length) {
					if (length < 1024 && commonByteArray_ORANGE_Time.length == 0) {
						commonByteArray_ORANGE_Time = new byte[1024];
					} else {
						commonByteArray_ORANGE_Time = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_Time, 0, length);
				strReturn = new String(commonByteArray_ORANGE_Time, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_ORANGE_Time) {

				try {

					int length = 0;

					this.PK_Date = readDate(dis);

					this.Date_Name = readString(dis);

					this.Year = readDate(dis);

					this.Year_Name = readString(dis);

					this.Half_Year = readDate(dis);

					this.Half_Year_Name = readString(dis);

					this.Quarter = readDate(dis);

					this.Quarter_Name = readString(dis);

					this.Trimester = readDate(dis);

					this.Trimester_Name = readString(dis);

					this.Month = readDate(dis);

					this.Month_Name = readString(dis);

					this.Day_Of_Year = readInteger(dis);

					this.Day_Of_Year_Name = readString(dis);

					this.Day_Of_Half_Year = readInteger(dis);

					this.Day_Of_Half_Year_Name = readString(dis);

					this.Day_Of_Trimester = readInteger(dis);

					this.Day_Of_Trimester_Name = readString(dis);

					this.Day_Of_Quarter = readInteger(dis);

					this.Day_Of_Quarter_Name = readString(dis);

					this.Day_Of_Month = readInteger(dis);

					this.Day_Of_Month_Name = readString(dis);

					this.Month_Of_Year = readInteger(dis);

					this.Month_Of_Year_Name = readString(dis);

					this.Month_Of_Half_Year = readInteger(dis);

					this.Month_Of_Half_Year_Name = readString(dis);

					this.Month_Of_Trimester = readInteger(dis);

					this.Month_Of_Trimester_Name = readString(dis);

					this.Month_Of_Quarter = readInteger(dis);

					this.Month_Of_Quarter_Name = readString(dis);

					this.Quarter_Of_Year = readInteger(dis);

					this.Quarter_Of_Year_Name = readString(dis);

					this.Quarter_Of_Half_Year = readInteger(dis);

					this.Quarter_Of_Half_Year_Name = readString(dis);

					this.Trimester_Of_Year = readInteger(dis);

					this.Trimester_Of_Year_Name = readString(dis);

					this.Half_Year_Of_Year = readInteger(dis);

					this.Half_Year_Of_Year_Name = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_Time) {

				try {

					int length = 0;

					this.PK_Date = readDate(dis);

					this.Date_Name = readString(dis);

					this.Year = readDate(dis);

					this.Year_Name = readString(dis);

					this.Half_Year = readDate(dis);

					this.Half_Year_Name = readString(dis);

					this.Quarter = readDate(dis);

					this.Quarter_Name = readString(dis);

					this.Trimester = readDate(dis);

					this.Trimester_Name = readString(dis);

					this.Month = readDate(dis);

					this.Month_Name = readString(dis);

					this.Day_Of_Year = readInteger(dis);

					this.Day_Of_Year_Name = readString(dis);

					this.Day_Of_Half_Year = readInteger(dis);

					this.Day_Of_Half_Year_Name = readString(dis);

					this.Day_Of_Trimester = readInteger(dis);

					this.Day_Of_Trimester_Name = readString(dis);

					this.Day_Of_Quarter = readInteger(dis);

					this.Day_Of_Quarter_Name = readString(dis);

					this.Day_Of_Month = readInteger(dis);

					this.Day_Of_Month_Name = readString(dis);

					this.Month_Of_Year = readInteger(dis);

					this.Month_Of_Year_Name = readString(dis);

					this.Month_Of_Half_Year = readInteger(dis);

					this.Month_Of_Half_Year_Name = readString(dis);

					this.Month_Of_Trimester = readInteger(dis);

					this.Month_Of_Trimester_Name = readString(dis);

					this.Month_Of_Quarter = readInteger(dis);

					this.Month_Of_Quarter_Name = readString(dis);

					this.Quarter_Of_Year = readInteger(dis);

					this.Quarter_Of_Year_Name = readString(dis);

					this.Quarter_Of_Half_Year = readInteger(dis);

					this.Quarter_Of_Half_Year_Name = readString(dis);

					this.Trimester_Of_Year = readInteger(dis);

					this.Trimester_Of_Year_Name = readString(dis);

					this.Half_Year_Of_Year = readInteger(dis);

					this.Half_Year_Of_Year_Name = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// java.util.Date

				writeDate(this.PK_Date, dos);

				// String

				writeString(this.Date_Name, dos);

				// java.util.Date

				writeDate(this.Year, dos);

				// String

				writeString(this.Year_Name, dos);

				// java.util.Date

				writeDate(this.Half_Year, dos);

				// String

				writeString(this.Half_Year_Name, dos);

				// java.util.Date

				writeDate(this.Quarter, dos);

				// String

				writeString(this.Quarter_Name, dos);

				// java.util.Date

				writeDate(this.Trimester, dos);

				// String

				writeString(this.Trimester_Name, dos);

				// java.util.Date

				writeDate(this.Month, dos);

				// String

				writeString(this.Month_Name, dos);

				// Integer

				writeInteger(this.Day_Of_Year, dos);

				// String

				writeString(this.Day_Of_Year_Name, dos);

				// Integer

				writeInteger(this.Day_Of_Half_Year, dos);

				// String

				writeString(this.Day_Of_Half_Year_Name, dos);

				// Integer

				writeInteger(this.Day_Of_Trimester, dos);

				// String

				writeString(this.Day_Of_Trimester_Name, dos);

				// Integer

				writeInteger(this.Day_Of_Quarter, dos);

				// String

				writeString(this.Day_Of_Quarter_Name, dos);

				// Integer

				writeInteger(this.Day_Of_Month, dos);

				// String

				writeString(this.Day_Of_Month_Name, dos);

				// Integer

				writeInteger(this.Month_Of_Year, dos);

				// String

				writeString(this.Month_Of_Year_Name, dos);

				// Integer

				writeInteger(this.Month_Of_Half_Year, dos);

				// String

				writeString(this.Month_Of_Half_Year_Name, dos);

				// Integer

				writeInteger(this.Month_Of_Trimester, dos);

				// String

				writeString(this.Month_Of_Trimester_Name, dos);

				// Integer

				writeInteger(this.Month_Of_Quarter, dos);

				// String

				writeString(this.Month_Of_Quarter_Name, dos);

				// Integer

				writeInteger(this.Quarter_Of_Year, dos);

				// String

				writeString(this.Quarter_Of_Year_Name, dos);

				// Integer

				writeInteger(this.Quarter_Of_Half_Year, dos);

				// String

				writeString(this.Quarter_Of_Half_Year_Name, dos);

				// Integer

				writeInteger(this.Trimester_Of_Year, dos);

				// String

				writeString(this.Trimester_Of_Year_Name, dos);

				// Integer

				writeInteger(this.Half_Year_Of_Year, dos);

				// String

				writeString(this.Half_Year_Of_Year_Name, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// java.util.Date

				writeDate(this.PK_Date, dos);

				// String

				writeString(this.Date_Name, dos);

				// java.util.Date

				writeDate(this.Year, dos);

				// String

				writeString(this.Year_Name, dos);

				// java.util.Date

				writeDate(this.Half_Year, dos);

				// String

				writeString(this.Half_Year_Name, dos);

				// java.util.Date

				writeDate(this.Quarter, dos);

				// String

				writeString(this.Quarter_Name, dos);

				// java.util.Date

				writeDate(this.Trimester, dos);

				// String

				writeString(this.Trimester_Name, dos);

				// java.util.Date

				writeDate(this.Month, dos);

				// String

				writeString(this.Month_Name, dos);

				// Integer

				writeInteger(this.Day_Of_Year, dos);

				// String

				writeString(this.Day_Of_Year_Name, dos);

				// Integer

				writeInteger(this.Day_Of_Half_Year, dos);

				// String

				writeString(this.Day_Of_Half_Year_Name, dos);

				// Integer

				writeInteger(this.Day_Of_Trimester, dos);

				// String

				writeString(this.Day_Of_Trimester_Name, dos);

				// Integer

				writeInteger(this.Day_Of_Quarter, dos);

				// String

				writeString(this.Day_Of_Quarter_Name, dos);

				// Integer

				writeInteger(this.Day_Of_Month, dos);

				// String

				writeString(this.Day_Of_Month_Name, dos);

				// Integer

				writeInteger(this.Month_Of_Year, dos);

				// String

				writeString(this.Month_Of_Year_Name, dos);

				// Integer

				writeInteger(this.Month_Of_Half_Year, dos);

				// String

				writeString(this.Month_Of_Half_Year_Name, dos);

				// Integer

				writeInteger(this.Month_Of_Trimester, dos);

				// String

				writeString(this.Month_Of_Trimester_Name, dos);

				// Integer

				writeInteger(this.Month_Of_Quarter, dos);

				// String

				writeString(this.Month_Of_Quarter_Name, dos);

				// Integer

				writeInteger(this.Quarter_Of_Year, dos);

				// String

				writeString(this.Quarter_Of_Year_Name, dos);

				// Integer

				writeInteger(this.Quarter_Of_Half_Year, dos);

				// String

				writeString(this.Quarter_Of_Half_Year_Name, dos);

				// Integer

				writeInteger(this.Trimester_Of_Year, dos);

				// String

				writeString(this.Trimester_Of_Year_Name, dos);

				// Integer

				writeInteger(this.Half_Year_Of_Year, dos);

				// String

				writeString(this.Half_Year_Of_Year_Name, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("PK_Date=" + String.valueOf(PK_Date));
			sb.append(",Date_Name=" + Date_Name);
			sb.append(",Year=" + String.valueOf(Year));
			sb.append(",Year_Name=" + Year_Name);
			sb.append(",Half_Year=" + String.valueOf(Half_Year));
			sb.append(",Half_Year_Name=" + Half_Year_Name);
			sb.append(",Quarter=" + String.valueOf(Quarter));
			sb.append(",Quarter_Name=" + Quarter_Name);
			sb.append(",Trimester=" + String.valueOf(Trimester));
			sb.append(",Trimester_Name=" + Trimester_Name);
			sb.append(",Month=" + String.valueOf(Month));
			sb.append(",Month_Name=" + Month_Name);
			sb.append(",Day_Of_Year=" + String.valueOf(Day_Of_Year));
			sb.append(",Day_Of_Year_Name=" + Day_Of_Year_Name);
			sb.append(",Day_Of_Half_Year=" + String.valueOf(Day_Of_Half_Year));
			sb.append(",Day_Of_Half_Year_Name=" + Day_Of_Half_Year_Name);
			sb.append(",Day_Of_Trimester=" + String.valueOf(Day_Of_Trimester));
			sb.append(",Day_Of_Trimester_Name=" + Day_Of_Trimester_Name);
			sb.append(",Day_Of_Quarter=" + String.valueOf(Day_Of_Quarter));
			sb.append(",Day_Of_Quarter_Name=" + Day_Of_Quarter_Name);
			sb.append(",Day_Of_Month=" + String.valueOf(Day_Of_Month));
			sb.append(",Day_Of_Month_Name=" + Day_Of_Month_Name);
			sb.append(",Month_Of_Year=" + String.valueOf(Month_Of_Year));
			sb.append(",Month_Of_Year_Name=" + Month_Of_Year_Name);
			sb.append(",Month_Of_Half_Year=" + String.valueOf(Month_Of_Half_Year));
			sb.append(",Month_Of_Half_Year_Name=" + Month_Of_Half_Year_Name);
			sb.append(",Month_Of_Trimester=" + String.valueOf(Month_Of_Trimester));
			sb.append(",Month_Of_Trimester_Name=" + Month_Of_Trimester_Name);
			sb.append(",Month_Of_Quarter=" + String.valueOf(Month_Of_Quarter));
			sb.append(",Month_Of_Quarter_Name=" + Month_Of_Quarter_Name);
			sb.append(",Quarter_Of_Year=" + String.valueOf(Quarter_Of_Year));
			sb.append(",Quarter_Of_Year_Name=" + Quarter_Of_Year_Name);
			sb.append(",Quarter_Of_Half_Year=" + String.valueOf(Quarter_Of_Half_Year));
			sb.append(",Quarter_Of_Half_Year_Name=" + Quarter_Of_Half_Year_Name);
			sb.append(",Trimester_Of_Year=" + String.valueOf(Trimester_Of_Year));
			sb.append(",Trimester_Of_Year_Name=" + Trimester_Of_Year_Name);
			sb.append(",Half_Year_Of_Year=" + String.valueOf(Half_Year_Of_Year));
			sb.append(",Half_Year_Of_Year_Name=" + Half_Year_Of_Year_Name);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(zzzStruct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.PK_Date, other.PK_Date);
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

	public static class row1Struct implements routines.system.IPersistableRow<row1Struct> {
		final static byte[] commonByteArrayLock_ORANGE_Time = new byte[0];
		static byte[] commonByteArray_ORANGE_Time = new byte[0];

		public java.util.Date PK_Date;

		public java.util.Date getPK_Date() {
			return this.PK_Date;
		}

		public String Date_Name;

		public String getDate_Name() {
			return this.Date_Name;
		}

		public java.util.Date Year;

		public java.util.Date getYear() {
			return this.Year;
		}

		public String Year_Name;

		public String getYear_Name() {
			return this.Year_Name;
		}

		public java.util.Date Half_Year;

		public java.util.Date getHalf_Year() {
			return this.Half_Year;
		}

		public String Half_Year_Name;

		public String getHalf_Year_Name() {
			return this.Half_Year_Name;
		}

		public java.util.Date Quarter;

		public java.util.Date getQuarter() {
			return this.Quarter;
		}

		public String Quarter_Name;

		public String getQuarter_Name() {
			return this.Quarter_Name;
		}

		public java.util.Date Trimester;

		public java.util.Date getTrimester() {
			return this.Trimester;
		}

		public String Trimester_Name;

		public String getTrimester_Name() {
			return this.Trimester_Name;
		}

		public java.util.Date Month;

		public java.util.Date getMonth() {
			return this.Month;
		}

		public String Month_Name;

		public String getMonth_Name() {
			return this.Month_Name;
		}

		public Integer Day_Of_Year;

		public Integer getDay_Of_Year() {
			return this.Day_Of_Year;
		}

		public String Day_Of_Year_Name;

		public String getDay_Of_Year_Name() {
			return this.Day_Of_Year_Name;
		}

		public Integer Day_Of_Half_Year;

		public Integer getDay_Of_Half_Year() {
			return this.Day_Of_Half_Year;
		}

		public String Day_Of_Half_Year_Name;

		public String getDay_Of_Half_Year_Name() {
			return this.Day_Of_Half_Year_Name;
		}

		public Integer Day_Of_Trimester;

		public Integer getDay_Of_Trimester() {
			return this.Day_Of_Trimester;
		}

		public String Day_Of_Trimester_Name;

		public String getDay_Of_Trimester_Name() {
			return this.Day_Of_Trimester_Name;
		}

		public Integer Day_Of_Quarter;

		public Integer getDay_Of_Quarter() {
			return this.Day_Of_Quarter;
		}

		public String Day_Of_Quarter_Name;

		public String getDay_Of_Quarter_Name() {
			return this.Day_Of_Quarter_Name;
		}

		public Integer Day_Of_Month;

		public Integer getDay_Of_Month() {
			return this.Day_Of_Month;
		}

		public String Day_Of_Month_Name;

		public String getDay_Of_Month_Name() {
			return this.Day_Of_Month_Name;
		}

		public Integer Month_Of_Year;

		public Integer getMonth_Of_Year() {
			return this.Month_Of_Year;
		}

		public String Month_Of_Year_Name;

		public String getMonth_Of_Year_Name() {
			return this.Month_Of_Year_Name;
		}

		public Integer Month_Of_Half_Year;

		public Integer getMonth_Of_Half_Year() {
			return this.Month_Of_Half_Year;
		}

		public String Month_Of_Half_Year_Name;

		public String getMonth_Of_Half_Year_Name() {
			return this.Month_Of_Half_Year_Name;
		}

		public Integer Month_Of_Trimester;

		public Integer getMonth_Of_Trimester() {
			return this.Month_Of_Trimester;
		}

		public String Month_Of_Trimester_Name;

		public String getMonth_Of_Trimester_Name() {
			return this.Month_Of_Trimester_Name;
		}

		public Integer Month_Of_Quarter;

		public Integer getMonth_Of_Quarter() {
			return this.Month_Of_Quarter;
		}

		public String Month_Of_Quarter_Name;

		public String getMonth_Of_Quarter_Name() {
			return this.Month_Of_Quarter_Name;
		}

		public Integer Quarter_Of_Year;

		public Integer getQuarter_Of_Year() {
			return this.Quarter_Of_Year;
		}

		public String Quarter_Of_Year_Name;

		public String getQuarter_Of_Year_Name() {
			return this.Quarter_Of_Year_Name;
		}

		public Integer Quarter_Of_Half_Year;

		public Integer getQuarter_Of_Half_Year() {
			return this.Quarter_Of_Half_Year;
		}

		public String Quarter_Of_Half_Year_Name;

		public String getQuarter_Of_Half_Year_Name() {
			return this.Quarter_Of_Half_Year_Name;
		}

		public Integer Trimester_Of_Year;

		public Integer getTrimester_Of_Year() {
			return this.Trimester_Of_Year;
		}

		public String Trimester_Of_Year_Name;

		public String getTrimester_Of_Year_Name() {
			return this.Trimester_Of_Year_Name;
		}

		public Integer Half_Year_Of_Year;

		public Integer getHalf_Year_Of_Year() {
			return this.Half_Year_Of_Year;
		}

		public String Half_Year_Of_Year_Name;

		public String getHalf_Year_Of_Year_Name() {
			return this.Half_Year_Of_Year_Name;
		}

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_Time.length) {
					if (length < 1024 && commonByteArray_ORANGE_Time.length == 0) {
						commonByteArray_ORANGE_Time = new byte[1024];
					} else {
						commonByteArray_ORANGE_Time = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_Time, 0, length);
				strReturn = new String(commonByteArray_ORANGE_Time, 0, length, utf8Charset);
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
				if (length > commonByteArray_ORANGE_Time.length) {
					if (length < 1024 && commonByteArray_ORANGE_Time.length == 0) {
						commonByteArray_ORANGE_Time = new byte[1024];
					} else {
						commonByteArray_ORANGE_Time = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_Time, 0, length);
				strReturn = new String(commonByteArray_ORANGE_Time, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_ORANGE_Time) {

				try {

					int length = 0;

					this.PK_Date = readDate(dis);

					this.Date_Name = readString(dis);

					this.Year = readDate(dis);

					this.Year_Name = readString(dis);

					this.Half_Year = readDate(dis);

					this.Half_Year_Name = readString(dis);

					this.Quarter = readDate(dis);

					this.Quarter_Name = readString(dis);

					this.Trimester = readDate(dis);

					this.Trimester_Name = readString(dis);

					this.Month = readDate(dis);

					this.Month_Name = readString(dis);

					this.Day_Of_Year = readInteger(dis);

					this.Day_Of_Year_Name = readString(dis);

					this.Day_Of_Half_Year = readInteger(dis);

					this.Day_Of_Half_Year_Name = readString(dis);

					this.Day_Of_Trimester = readInteger(dis);

					this.Day_Of_Trimester_Name = readString(dis);

					this.Day_Of_Quarter = readInteger(dis);

					this.Day_Of_Quarter_Name = readString(dis);

					this.Day_Of_Month = readInteger(dis);

					this.Day_Of_Month_Name = readString(dis);

					this.Month_Of_Year = readInteger(dis);

					this.Month_Of_Year_Name = readString(dis);

					this.Month_Of_Half_Year = readInteger(dis);

					this.Month_Of_Half_Year_Name = readString(dis);

					this.Month_Of_Trimester = readInteger(dis);

					this.Month_Of_Trimester_Name = readString(dis);

					this.Month_Of_Quarter = readInteger(dis);

					this.Month_Of_Quarter_Name = readString(dis);

					this.Quarter_Of_Year = readInteger(dis);

					this.Quarter_Of_Year_Name = readString(dis);

					this.Quarter_Of_Half_Year = readInteger(dis);

					this.Quarter_Of_Half_Year_Name = readString(dis);

					this.Trimester_Of_Year = readInteger(dis);

					this.Trimester_Of_Year_Name = readString(dis);

					this.Half_Year_Of_Year = readInteger(dis);

					this.Half_Year_Of_Year_Name = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_Time) {

				try {

					int length = 0;

					this.PK_Date = readDate(dis);

					this.Date_Name = readString(dis);

					this.Year = readDate(dis);

					this.Year_Name = readString(dis);

					this.Half_Year = readDate(dis);

					this.Half_Year_Name = readString(dis);

					this.Quarter = readDate(dis);

					this.Quarter_Name = readString(dis);

					this.Trimester = readDate(dis);

					this.Trimester_Name = readString(dis);

					this.Month = readDate(dis);

					this.Month_Name = readString(dis);

					this.Day_Of_Year = readInteger(dis);

					this.Day_Of_Year_Name = readString(dis);

					this.Day_Of_Half_Year = readInteger(dis);

					this.Day_Of_Half_Year_Name = readString(dis);

					this.Day_Of_Trimester = readInteger(dis);

					this.Day_Of_Trimester_Name = readString(dis);

					this.Day_Of_Quarter = readInteger(dis);

					this.Day_Of_Quarter_Name = readString(dis);

					this.Day_Of_Month = readInteger(dis);

					this.Day_Of_Month_Name = readString(dis);

					this.Month_Of_Year = readInteger(dis);

					this.Month_Of_Year_Name = readString(dis);

					this.Month_Of_Half_Year = readInteger(dis);

					this.Month_Of_Half_Year_Name = readString(dis);

					this.Month_Of_Trimester = readInteger(dis);

					this.Month_Of_Trimester_Name = readString(dis);

					this.Month_Of_Quarter = readInteger(dis);

					this.Month_Of_Quarter_Name = readString(dis);

					this.Quarter_Of_Year = readInteger(dis);

					this.Quarter_Of_Year_Name = readString(dis);

					this.Quarter_Of_Half_Year = readInteger(dis);

					this.Quarter_Of_Half_Year_Name = readString(dis);

					this.Trimester_Of_Year = readInteger(dis);

					this.Trimester_Of_Year_Name = readString(dis);

					this.Half_Year_Of_Year = readInteger(dis);

					this.Half_Year_Of_Year_Name = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// java.util.Date

				writeDate(this.PK_Date, dos);

				// String

				writeString(this.Date_Name, dos);

				// java.util.Date

				writeDate(this.Year, dos);

				// String

				writeString(this.Year_Name, dos);

				// java.util.Date

				writeDate(this.Half_Year, dos);

				// String

				writeString(this.Half_Year_Name, dos);

				// java.util.Date

				writeDate(this.Quarter, dos);

				// String

				writeString(this.Quarter_Name, dos);

				// java.util.Date

				writeDate(this.Trimester, dos);

				// String

				writeString(this.Trimester_Name, dos);

				// java.util.Date

				writeDate(this.Month, dos);

				// String

				writeString(this.Month_Name, dos);

				// Integer

				writeInteger(this.Day_Of_Year, dos);

				// String

				writeString(this.Day_Of_Year_Name, dos);

				// Integer

				writeInteger(this.Day_Of_Half_Year, dos);

				// String

				writeString(this.Day_Of_Half_Year_Name, dos);

				// Integer

				writeInteger(this.Day_Of_Trimester, dos);

				// String

				writeString(this.Day_Of_Trimester_Name, dos);

				// Integer

				writeInteger(this.Day_Of_Quarter, dos);

				// String

				writeString(this.Day_Of_Quarter_Name, dos);

				// Integer

				writeInteger(this.Day_Of_Month, dos);

				// String

				writeString(this.Day_Of_Month_Name, dos);

				// Integer

				writeInteger(this.Month_Of_Year, dos);

				// String

				writeString(this.Month_Of_Year_Name, dos);

				// Integer

				writeInteger(this.Month_Of_Half_Year, dos);

				// String

				writeString(this.Month_Of_Half_Year_Name, dos);

				// Integer

				writeInteger(this.Month_Of_Trimester, dos);

				// String

				writeString(this.Month_Of_Trimester_Name, dos);

				// Integer

				writeInteger(this.Month_Of_Quarter, dos);

				// String

				writeString(this.Month_Of_Quarter_Name, dos);

				// Integer

				writeInteger(this.Quarter_Of_Year, dos);

				// String

				writeString(this.Quarter_Of_Year_Name, dos);

				// Integer

				writeInteger(this.Quarter_Of_Half_Year, dos);

				// String

				writeString(this.Quarter_Of_Half_Year_Name, dos);

				// Integer

				writeInteger(this.Trimester_Of_Year, dos);

				// String

				writeString(this.Trimester_Of_Year_Name, dos);

				// Integer

				writeInteger(this.Half_Year_Of_Year, dos);

				// String

				writeString(this.Half_Year_Of_Year_Name, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// java.util.Date

				writeDate(this.PK_Date, dos);

				// String

				writeString(this.Date_Name, dos);

				// java.util.Date

				writeDate(this.Year, dos);

				// String

				writeString(this.Year_Name, dos);

				// java.util.Date

				writeDate(this.Half_Year, dos);

				// String

				writeString(this.Half_Year_Name, dos);

				// java.util.Date

				writeDate(this.Quarter, dos);

				// String

				writeString(this.Quarter_Name, dos);

				// java.util.Date

				writeDate(this.Trimester, dos);

				// String

				writeString(this.Trimester_Name, dos);

				// java.util.Date

				writeDate(this.Month, dos);

				// String

				writeString(this.Month_Name, dos);

				// Integer

				writeInteger(this.Day_Of_Year, dos);

				// String

				writeString(this.Day_Of_Year_Name, dos);

				// Integer

				writeInteger(this.Day_Of_Half_Year, dos);

				// String

				writeString(this.Day_Of_Half_Year_Name, dos);

				// Integer

				writeInteger(this.Day_Of_Trimester, dos);

				// String

				writeString(this.Day_Of_Trimester_Name, dos);

				// Integer

				writeInteger(this.Day_Of_Quarter, dos);

				// String

				writeString(this.Day_Of_Quarter_Name, dos);

				// Integer

				writeInteger(this.Day_Of_Month, dos);

				// String

				writeString(this.Day_Of_Month_Name, dos);

				// Integer

				writeInteger(this.Month_Of_Year, dos);

				// String

				writeString(this.Month_Of_Year_Name, dos);

				// Integer

				writeInteger(this.Month_Of_Half_Year, dos);

				// String

				writeString(this.Month_Of_Half_Year_Name, dos);

				// Integer

				writeInteger(this.Month_Of_Trimester, dos);

				// String

				writeString(this.Month_Of_Trimester_Name, dos);

				// Integer

				writeInteger(this.Month_Of_Quarter, dos);

				// String

				writeString(this.Month_Of_Quarter_Name, dos);

				// Integer

				writeInteger(this.Quarter_Of_Year, dos);

				// String

				writeString(this.Quarter_Of_Year_Name, dos);

				// Integer

				writeInteger(this.Quarter_Of_Half_Year, dos);

				// String

				writeString(this.Quarter_Of_Half_Year_Name, dos);

				// Integer

				writeInteger(this.Trimester_Of_Year, dos);

				// String

				writeString(this.Trimester_Of_Year_Name, dos);

				// Integer

				writeInteger(this.Half_Year_Of_Year, dos);

				// String

				writeString(this.Half_Year_Of_Year_Name, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("PK_Date=" + String.valueOf(PK_Date));
			sb.append(",Date_Name=" + Date_Name);
			sb.append(",Year=" + String.valueOf(Year));
			sb.append(",Year_Name=" + Year_Name);
			sb.append(",Half_Year=" + String.valueOf(Half_Year));
			sb.append(",Half_Year_Name=" + Half_Year_Name);
			sb.append(",Quarter=" + String.valueOf(Quarter));
			sb.append(",Quarter_Name=" + Quarter_Name);
			sb.append(",Trimester=" + String.valueOf(Trimester));
			sb.append(",Trimester_Name=" + Trimester_Name);
			sb.append(",Month=" + String.valueOf(Month));
			sb.append(",Month_Name=" + Month_Name);
			sb.append(",Day_Of_Year=" + String.valueOf(Day_Of_Year));
			sb.append(",Day_Of_Year_Name=" + Day_Of_Year_Name);
			sb.append(",Day_Of_Half_Year=" + String.valueOf(Day_Of_Half_Year));
			sb.append(",Day_Of_Half_Year_Name=" + Day_Of_Half_Year_Name);
			sb.append(",Day_Of_Trimester=" + String.valueOf(Day_Of_Trimester));
			sb.append(",Day_Of_Trimester_Name=" + Day_Of_Trimester_Name);
			sb.append(",Day_Of_Quarter=" + String.valueOf(Day_Of_Quarter));
			sb.append(",Day_Of_Quarter_Name=" + Day_Of_Quarter_Name);
			sb.append(",Day_Of_Month=" + String.valueOf(Day_Of_Month));
			sb.append(",Day_Of_Month_Name=" + Day_Of_Month_Name);
			sb.append(",Month_Of_Year=" + String.valueOf(Month_Of_Year));
			sb.append(",Month_Of_Year_Name=" + Month_Of_Year_Name);
			sb.append(",Month_Of_Half_Year=" + String.valueOf(Month_Of_Half_Year));
			sb.append(",Month_Of_Half_Year_Name=" + Month_Of_Half_Year_Name);
			sb.append(",Month_Of_Trimester=" + String.valueOf(Month_Of_Trimester));
			sb.append(",Month_Of_Trimester_Name=" + Month_Of_Trimester_Name);
			sb.append(",Month_Of_Quarter=" + String.valueOf(Month_Of_Quarter));
			sb.append(",Month_Of_Quarter_Name=" + Month_Of_Quarter_Name);
			sb.append(",Quarter_Of_Year=" + String.valueOf(Quarter_Of_Year));
			sb.append(",Quarter_Of_Year_Name=" + Quarter_Of_Year_Name);
			sb.append(",Quarter_Of_Half_Year=" + String.valueOf(Quarter_Of_Half_Year));
			sb.append(",Quarter_Of_Half_Year_Name=" + Quarter_Of_Half_Year_Name);
			sb.append(",Trimester_Of_Year=" + String.valueOf(Trimester_Of_Year));
			sb.append(",Trimester_Of_Year_Name=" + Trimester_Of_Year_Name);
			sb.append(",Half_Year_Of_Year=" + String.valueOf(Half_Year_Of_Year));
			sb.append(",Half_Year_Of_Year_Name=" + Half_Year_Of_Year_Name);
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

	public void tDBInput_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBInput_1_SUBPROCESS_STATE", 0);

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

				row1Struct row1 = new row1Struct();
				zzzStruct zzz = new zzzStruct();

				/**
				 * [tDBOutput_1 begin ] start
				 */

				ok_Hash.put("tDBOutput_1", false);
				start_Hash.put("tDBOutput_1", System.currentTimeMillis());

				currentComponent = "tDBOutput_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "zzz");
				}

				int tos_count_tDBOutput_1 = 0;

				int nb_line_tDBOutput_1 = 0;
				int nb_line_update_tDBOutput_1 = 0;
				int nb_line_inserted_tDBOutput_1 = 0;
				int nb_line_deleted_tDBOutput_1 = 0;
				int nb_line_rejected_tDBOutput_1 = 0;

				int deletedCount_tDBOutput_1 = 0;
				int updatedCount_tDBOutput_1 = 0;
				int insertedCount_tDBOutput_1 = 0;
				int rowsToCommitCount_tDBOutput_1 = 0;
				int rejectedCount_tDBOutput_1 = 0;
				String dbschema_tDBOutput_1 = null;
				String tableName_tDBOutput_1 = null;
				boolean whetherReject_tDBOutput_1 = false;

				java.util.Calendar calendar_tDBOutput_1 = java.util.Calendar.getInstance();
				long year1_tDBOutput_1 = TalendDate.parseDate("yyyy-MM-dd", "0001-01-01").getTime();
				long year2_tDBOutput_1 = TalendDate.parseDate("yyyy-MM-dd", "1753-01-01").getTime();
				long year10000_tDBOutput_1 = TalendDate.parseDate("yyyy-MM-dd HH:mm:ss", "9999-12-31 24:00:00")
						.getTime();
				long date_tDBOutput_1;

				java.util.Calendar calendar_datetimeoffset_tDBOutput_1 = java.util.Calendar
						.getInstance(java.util.TimeZone.getTimeZone("UTC"));

				java.sql.Connection conn_tDBOutput_1 = null;
				String dbUser_tDBOutput_1 = null;
				dbschema_tDBOutput_1 = "dbo";
				String driverClass_tDBOutput_1 = "net.sourceforge.jtds.jdbc.Driver";

				java.lang.Class.forName(driverClass_tDBOutput_1);
				String port_tDBOutput_1 = "1433";
				String dbname_tDBOutput_1 = "orange_DW__";
				String url_tDBOutput_1 = "jdbc:jtds:sqlserver://" + "DESKTOP-6RL9E91";
				if (!"".equals(port_tDBOutput_1)) {
					url_tDBOutput_1 += ":" + "1433";
				}
				if (!"".equals(dbname_tDBOutput_1)) {
					url_tDBOutput_1 += "//" + "orange_DW__";

				}
				url_tDBOutput_1 += ";appName=" + projectName + ";" + "";
				dbUser_tDBOutput_1 = "sa";

				final String decryptedPassword_tDBOutput_1 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:wMZ7/RBumjUjAItoWNIZauI28d/C4Fp2tOm5DSfNAI+JKSl+GY8=");

				String dbPwd_tDBOutput_1 = decryptedPassword_tDBOutput_1;
				conn_tDBOutput_1 = java.sql.DriverManager.getConnection(url_tDBOutput_1, dbUser_tDBOutput_1,
						dbPwd_tDBOutput_1);

				resourceMap.put("conn_tDBOutput_1", conn_tDBOutput_1);

				conn_tDBOutput_1.setAutoCommit(false);
				int commitEvery_tDBOutput_1 = 10000;
				int commitCounter_tDBOutput_1 = 0;

				int batchSize_tDBOutput_1 = 10000;
				int batchSizeCounter_tDBOutput_1 = 0;

				if (dbschema_tDBOutput_1 == null || dbschema_tDBOutput_1.trim().length() == 0) {
					tableName_tDBOutput_1 = "Time";
				} else {
					tableName_tDBOutput_1 = dbschema_tDBOutput_1 + "].[" + "Time";
				}
				int count_tDBOutput_1 = 0;

				String insert_tDBOutput_1 = "INSERT INTO [" + tableName_tDBOutput_1
						+ "] ([PK_Date],[Date_Name],[Year],[Year_Name],[Half_Year],[Half_Year_Name],[Quarter],[Quarter_Name],[Trimester],[Trimester_Name],[Month],[Month_Name],[Day_Of_Year],[Day_Of_Year_Name],[Day_Of_Half_Year],[Day_Of_Half_Year_Name],[Day_Of_Trimester],[Day_Of_Trimester_Name],[Day_Of_Quarter],[Day_Of_Quarter_Name],[Day_Of_Month],[Day_Of_Month_Name],[Month_Of_Year],[Month_Of_Year_Name],[Month_Of_Half_Year],[Month_Of_Half_Year_Name],[Month_Of_Trimester],[Month_Of_Trimester_Name],[Month_Of_Quarter],[Month_Of_Quarter_Name],[Quarter_Of_Year],[Quarter_Of_Year_Name],[Quarter_Of_Half_Year],[Quarter_Of_Half_Year_Name],[Trimester_Of_Year],[Trimester_Of_Year_Name],[Half_Year_Of_Year],[Half_Year_Of_Year_Name]) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				java.sql.PreparedStatement pstmt_tDBOutput_1 = conn_tDBOutput_1.prepareStatement(insert_tDBOutput_1);
				resourceMap.put("pstmt_tDBOutput_1", pstmt_tDBOutput_1);

				/**
				 * [tDBOutput_1 begin ] stop
				 */

				/**
				 * [tMap_1 begin ] start
				 */

				ok_Hash.put("tMap_1", false);
				start_Hash.put("tMap_1", System.currentTimeMillis());

				currentComponent = "tMap_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row1");
				}

				int tos_count_tMap_1 = 0;

// ###############################
// # Lookup's keys initialization
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_1__Struct {
				}
				Var__tMap_1__Struct Var__tMap_1 = new Var__tMap_1__Struct();
// ###############################

// ###############################
// # Outputs initialization
				zzzStruct zzz_tmp = new zzzStruct();
// ###############################

				/**
				 * [tMap_1 begin ] stop
				 */

				/**
				 * [tDBInput_1 begin ] start
				 */

				ok_Hash.put("tDBInput_1", false);
				start_Hash.put("tDBInput_1", System.currentTimeMillis());

				currentComponent = "tDBInput_1";

				int tos_count_tDBInput_1 = 0;

				org.talend.designer.components.util.mssql.MSSqlGenerateTimestampUtil mssqlGTU_tDBInput_1 = org.talend.designer.components.util.mssql.MSSqlUtilFactory
						.getMSSqlGenerateTimestampUtil();

				java.util.List<String> talendToDBList_tDBInput_1 = new java.util.ArrayList();
				String[] talendToDBArray_tDBInput_1 = new String[] { "FLOAT", "NUMERIC", "NUMERIC IDENTITY", "DECIMAL",
						"DECIMAL IDENTITY", "REAL" };
				java.util.Collections.addAll(talendToDBList_tDBInput_1, talendToDBArray_tDBInput_1);
				int nb_line_tDBInput_1 = 0;
				java.sql.Connection conn_tDBInput_1 = null;
				String driverClass_tDBInput_1 = "net.sourceforge.jtds.jdbc.Driver";
				java.lang.Class jdbcclazz_tDBInput_1 = java.lang.Class.forName(driverClass_tDBInput_1);
				String dbUser_tDBInput_1 = "sa";

				final String decryptedPassword_tDBInput_1 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:BnVXnc43yKKFc8LpRMCIhtfYeD5UazsDhiGWY29y3YRyzdneFbI=");

				String dbPwd_tDBInput_1 = decryptedPassword_tDBInput_1;

				String port_tDBInput_1 = "1433";
				String dbname_tDBInput_1 = "orange_DW__";
				String url_tDBInput_1 = "jdbc:jtds:sqlserver://" + "DESKTOP-6RL9E91";
				if (!"".equals(port_tDBInput_1)) {
					url_tDBInput_1 += ":" + "1433";
				}
				if (!"".equals(dbname_tDBInput_1)) {
					url_tDBInput_1 += "//" + "orange_DW__";
				}
				url_tDBInput_1 += ";appName=" + projectName + ";" + "";
				String dbschema_tDBInput_1 = "dbo";

				conn_tDBInput_1 = java.sql.DriverManager.getConnection(url_tDBInput_1, dbUser_tDBInput_1,
						dbPwd_tDBInput_1);

				java.sql.Statement stmt_tDBInput_1 = conn_tDBInput_1.createStatement();

				String dbquery_tDBInput_1 = "SELECT dbo.Time0.PK_Date,\n		dbo.Time0.Date_Name,\n		dbo.Time0.\"Year\",\n		dbo.Time0.Year_Name,\n		dbo.Time0.Half_Year,\n		"
						+ "dbo.Time0.Half_Year_Name,\n		dbo.Time0.Quarter,\n		dbo.Time0.Quarter_Name,\n		dbo.Time0.Trimester,\n		dbo.Time0.Trimester_Na"
						+ "me,\n		dbo.Time0.\"Month\",\n		dbo.Time0.Month_Name,\n		dbo.Time0.Day_Of_Year,\n		dbo.Time0.Day_Of_Year_Name,\n		dbo.Time0.Da"
						+ "y_Of_Half_Year,\n		dbo.Time0.Day_Of_Half_Year_Name,\n		dbo.Time0.Day_Of_Trimester,\n		dbo.Time0.Day_Of_Trimester_Name,\n		db"
						+ "o.Time0.Day_Of_Quarter,\n		dbo.Time0.Day_Of_Quarter_Name,\n		dbo.Time0.Day_Of_Month,\n		dbo.Time0.Day_Of_Month_Name,\n		dbo."
						+ "Time0.Month_Of_Year,\n		dbo.Time0.Month_Of_Year_Name,\n		dbo.Time0.Month_Of_Half_Year,\n		dbo.Time0.Month_Of_Half_Year_Name"
						+ ",\n		dbo.Time0.Month_Of_Trimester,\n		dbo.Time0.Month_Of_Trimester_Name,\n		dbo.Time0.Month_Of_Quarter,\n		dbo.Time0.Month_O"
						+ "f_Quarter_Name,\n		dbo.Time0.Quarter_Of_Year,\n		dbo.Time0.Quarter_Of_Year_Name,\n		dbo.Time0.Quarter_Of_Half_Year,\n		dbo.T"
						+ "ime0.Quarter_Of_Half_Year_Name,\n		dbo.Time0.Trimester_Of_Year,\n		dbo.Time0.Trimester_Of_Year_Name,\n		dbo.Time0.Half_Year"
						+ "_Of_Year,\n		dbo.Time0.Half_Year_Of_Year_Name\nFROM	dbo.Time0";

				globalMap.put("tDBInput_1_QUERY", dbquery_tDBInput_1);
				java.sql.ResultSet rs_tDBInput_1 = null;

				try {
					rs_tDBInput_1 = stmt_tDBInput_1.executeQuery(dbquery_tDBInput_1);
					java.sql.ResultSetMetaData rsmd_tDBInput_1 = rs_tDBInput_1.getMetaData();
					int colQtyInRs_tDBInput_1 = rsmd_tDBInput_1.getColumnCount();

					String tmpContent_tDBInput_1 = null;

					while (rs_tDBInput_1.next()) {
						nb_line_tDBInput_1++;

						if (colQtyInRs_tDBInput_1 < 1) {
							row1.PK_Date = null;
						} else {

							row1.PK_Date = mssqlGTU_tDBInput_1.getDate(rsmd_tDBInput_1, rs_tDBInput_1, 1);

						}
						if (colQtyInRs_tDBInput_1 < 2) {
							row1.Date_Name = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(2);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(2).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Date_Name = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Date_Name = tmpContent_tDBInput_1;
								}
							} else {
								row1.Date_Name = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 3) {
							row1.Year = null;
						} else {

							row1.Year = mssqlGTU_tDBInput_1.getDate(rsmd_tDBInput_1, rs_tDBInput_1, 3);

						}
						if (colQtyInRs_tDBInput_1 < 4) {
							row1.Year_Name = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(4);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(4).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Year_Name = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Year_Name = tmpContent_tDBInput_1;
								}
							} else {
								row1.Year_Name = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 5) {
							row1.Half_Year = null;
						} else {

							row1.Half_Year = mssqlGTU_tDBInput_1.getDate(rsmd_tDBInput_1, rs_tDBInput_1, 5);

						}
						if (colQtyInRs_tDBInput_1 < 6) {
							row1.Half_Year_Name = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(6);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(6).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Half_Year_Name = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Half_Year_Name = tmpContent_tDBInput_1;
								}
							} else {
								row1.Half_Year_Name = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 7) {
							row1.Quarter = null;
						} else {

							row1.Quarter = mssqlGTU_tDBInput_1.getDate(rsmd_tDBInput_1, rs_tDBInput_1, 7);

						}
						if (colQtyInRs_tDBInput_1 < 8) {
							row1.Quarter_Name = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(8);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(8).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Quarter_Name = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Quarter_Name = tmpContent_tDBInput_1;
								}
							} else {
								row1.Quarter_Name = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 9) {
							row1.Trimester = null;
						} else {

							row1.Trimester = mssqlGTU_tDBInput_1.getDate(rsmd_tDBInput_1, rs_tDBInput_1, 9);

						}
						if (colQtyInRs_tDBInput_1 < 10) {
							row1.Trimester_Name = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(10);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(10).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Trimester_Name = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Trimester_Name = tmpContent_tDBInput_1;
								}
							} else {
								row1.Trimester_Name = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 11) {
							row1.Month = null;
						} else {

							row1.Month = mssqlGTU_tDBInput_1.getDate(rsmd_tDBInput_1, rs_tDBInput_1, 11);

						}
						if (colQtyInRs_tDBInput_1 < 12) {
							row1.Month_Name = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(12);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(12).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Month_Name = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Month_Name = tmpContent_tDBInput_1;
								}
							} else {
								row1.Month_Name = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 13) {
							row1.Day_Of_Year = null;
						} else {

							row1.Day_Of_Year = rs_tDBInput_1.getInt(13);
							if (rs_tDBInput_1.wasNull()) {
								row1.Day_Of_Year = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 14) {
							row1.Day_Of_Year_Name = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(14);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(14).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Day_Of_Year_Name = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Day_Of_Year_Name = tmpContent_tDBInput_1;
								}
							} else {
								row1.Day_Of_Year_Name = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 15) {
							row1.Day_Of_Half_Year = null;
						} else {

							row1.Day_Of_Half_Year = rs_tDBInput_1.getInt(15);
							if (rs_tDBInput_1.wasNull()) {
								row1.Day_Of_Half_Year = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 16) {
							row1.Day_Of_Half_Year_Name = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(16);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(16).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Day_Of_Half_Year_Name = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Day_Of_Half_Year_Name = tmpContent_tDBInput_1;
								}
							} else {
								row1.Day_Of_Half_Year_Name = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 17) {
							row1.Day_Of_Trimester = null;
						} else {

							row1.Day_Of_Trimester = rs_tDBInput_1.getInt(17);
							if (rs_tDBInput_1.wasNull()) {
								row1.Day_Of_Trimester = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 18) {
							row1.Day_Of_Trimester_Name = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(18);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(18).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Day_Of_Trimester_Name = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Day_Of_Trimester_Name = tmpContent_tDBInput_1;
								}
							} else {
								row1.Day_Of_Trimester_Name = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 19) {
							row1.Day_Of_Quarter = null;
						} else {

							row1.Day_Of_Quarter = rs_tDBInput_1.getInt(19);
							if (rs_tDBInput_1.wasNull()) {
								row1.Day_Of_Quarter = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 20) {
							row1.Day_Of_Quarter_Name = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(20);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(20).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Day_Of_Quarter_Name = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Day_Of_Quarter_Name = tmpContent_tDBInput_1;
								}
							} else {
								row1.Day_Of_Quarter_Name = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 21) {
							row1.Day_Of_Month = null;
						} else {

							row1.Day_Of_Month = rs_tDBInput_1.getInt(21);
							if (rs_tDBInput_1.wasNull()) {
								row1.Day_Of_Month = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 22) {
							row1.Day_Of_Month_Name = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(22);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(22).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Day_Of_Month_Name = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Day_Of_Month_Name = tmpContent_tDBInput_1;
								}
							} else {
								row1.Day_Of_Month_Name = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 23) {
							row1.Month_Of_Year = null;
						} else {

							row1.Month_Of_Year = rs_tDBInput_1.getInt(23);
							if (rs_tDBInput_1.wasNull()) {
								row1.Month_Of_Year = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 24) {
							row1.Month_Of_Year_Name = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(24);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(24).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Month_Of_Year_Name = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Month_Of_Year_Name = tmpContent_tDBInput_1;
								}
							} else {
								row1.Month_Of_Year_Name = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 25) {
							row1.Month_Of_Half_Year = null;
						} else {

							row1.Month_Of_Half_Year = rs_tDBInput_1.getInt(25);
							if (rs_tDBInput_1.wasNull()) {
								row1.Month_Of_Half_Year = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 26) {
							row1.Month_Of_Half_Year_Name = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(26);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(26).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Month_Of_Half_Year_Name = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Month_Of_Half_Year_Name = tmpContent_tDBInput_1;
								}
							} else {
								row1.Month_Of_Half_Year_Name = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 27) {
							row1.Month_Of_Trimester = null;
						} else {

							row1.Month_Of_Trimester = rs_tDBInput_1.getInt(27);
							if (rs_tDBInput_1.wasNull()) {
								row1.Month_Of_Trimester = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 28) {
							row1.Month_Of_Trimester_Name = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(28);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(28).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Month_Of_Trimester_Name = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Month_Of_Trimester_Name = tmpContent_tDBInput_1;
								}
							} else {
								row1.Month_Of_Trimester_Name = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 29) {
							row1.Month_Of_Quarter = null;
						} else {

							row1.Month_Of_Quarter = rs_tDBInput_1.getInt(29);
							if (rs_tDBInput_1.wasNull()) {
								row1.Month_Of_Quarter = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 30) {
							row1.Month_Of_Quarter_Name = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(30);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(30).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Month_Of_Quarter_Name = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Month_Of_Quarter_Name = tmpContent_tDBInput_1;
								}
							} else {
								row1.Month_Of_Quarter_Name = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 31) {
							row1.Quarter_Of_Year = null;
						} else {

							row1.Quarter_Of_Year = rs_tDBInput_1.getInt(31);
							if (rs_tDBInput_1.wasNull()) {
								row1.Quarter_Of_Year = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 32) {
							row1.Quarter_Of_Year_Name = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(32);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(32).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Quarter_Of_Year_Name = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Quarter_Of_Year_Name = tmpContent_tDBInput_1;
								}
							} else {
								row1.Quarter_Of_Year_Name = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 33) {
							row1.Quarter_Of_Half_Year = null;
						} else {

							row1.Quarter_Of_Half_Year = rs_tDBInput_1.getInt(33);
							if (rs_tDBInput_1.wasNull()) {
								row1.Quarter_Of_Half_Year = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 34) {
							row1.Quarter_Of_Half_Year_Name = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(34);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(34).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Quarter_Of_Half_Year_Name = FormatterUtils
											.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Quarter_Of_Half_Year_Name = tmpContent_tDBInput_1;
								}
							} else {
								row1.Quarter_Of_Half_Year_Name = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 35) {
							row1.Trimester_Of_Year = null;
						} else {

							row1.Trimester_Of_Year = rs_tDBInput_1.getInt(35);
							if (rs_tDBInput_1.wasNull()) {
								row1.Trimester_Of_Year = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 36) {
							row1.Trimester_Of_Year_Name = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(36);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(36).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Trimester_Of_Year_Name = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Trimester_Of_Year_Name = tmpContent_tDBInput_1;
								}
							} else {
								row1.Trimester_Of_Year_Name = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 37) {
							row1.Half_Year_Of_Year = null;
						} else {

							row1.Half_Year_Of_Year = rs_tDBInput_1.getInt(37);
							if (rs_tDBInput_1.wasNull()) {
								row1.Half_Year_Of_Year = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 38) {
							row1.Half_Year_Of_Year_Name = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(38);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(38).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Half_Year_Of_Year_Name = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Half_Year_Of_Year_Name = tmpContent_tDBInput_1;
								}
							} else {
								row1.Half_Year_Of_Year_Name = null;
							}
						}

						/**
						 * [tDBInput_1 begin ] stop
						 */

						/**
						 * [tDBInput_1 main ] start
						 */

						currentComponent = "tDBInput_1";

						tos_count_tDBInput_1++;

						/**
						 * [tDBInput_1 main ] stop
						 */

						/**
						 * [tDBInput_1 process_data_begin ] start
						 */

						currentComponent = "tDBInput_1";

						/**
						 * [tDBInput_1 process_data_begin ] stop
						 */

						/**
						 * [tMap_1 main ] start
						 */

						currentComponent = "tMap_1";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row1"

							);
						}

						boolean hasCasePrimitiveKeyWithNull_tMap_1 = false;

						// ###############################
						// # Input tables (lookups)
						boolean rejectedInnerJoin_tMap_1 = false;
						boolean mainRowRejected_tMap_1 = false;

						// ###############################
						{ // start of Var scope

							// ###############################
							// # Vars tables

							Var__tMap_1__Struct Var = Var__tMap_1;// ###############################
							// ###############################
							// # Output tables

							zzz = null;

// # Output table : 'zzz'
							zzz_tmp.PK_Date = row1.PK_Date;
							zzz_tmp.Date_Name = row1.Date_Name;
							zzz_tmp.Year = row1.Year;
							zzz_tmp.Year_Name = row1.Year_Name;
							zzz_tmp.Half_Year = row1.Half_Year;
							zzz_tmp.Half_Year_Name = row1.Half_Year_Name;
							zzz_tmp.Quarter = row1.Quarter;
							zzz_tmp.Quarter_Name = row1.Quarter_Name;
							zzz_tmp.Trimester = row1.Trimester;
							zzz_tmp.Trimester_Name = row1.Trimester_Name;
							zzz_tmp.Month = row1.Month;
							zzz_tmp.Month_Name = row1.Month_Name;
							zzz_tmp.Day_Of_Year = row1.Day_Of_Year;
							zzz_tmp.Day_Of_Year_Name = row1.Day_Of_Year_Name;
							zzz_tmp.Day_Of_Half_Year = row1.Day_Of_Half_Year;
							zzz_tmp.Day_Of_Half_Year_Name = row1.Day_Of_Half_Year_Name;
							zzz_tmp.Day_Of_Trimester = row1.Day_Of_Trimester;
							zzz_tmp.Day_Of_Trimester_Name = row1.Day_Of_Trimester_Name;
							zzz_tmp.Day_Of_Quarter = row1.Day_Of_Quarter;
							zzz_tmp.Day_Of_Quarter_Name = row1.Day_Of_Quarter_Name;
							zzz_tmp.Day_Of_Month = row1.Day_Of_Month;
							zzz_tmp.Day_Of_Month_Name = row1.Day_Of_Month_Name;
							zzz_tmp.Month_Of_Year = row1.Month_Of_Year;
							zzz_tmp.Month_Of_Year_Name = row1.Month_Of_Year_Name;
							zzz_tmp.Month_Of_Half_Year = row1.Month_Of_Half_Year;
							zzz_tmp.Month_Of_Half_Year_Name = row1.Month_Of_Half_Year_Name;
							zzz_tmp.Month_Of_Trimester = row1.Month_Of_Trimester;
							zzz_tmp.Month_Of_Trimester_Name = row1.Month_Of_Trimester_Name;
							zzz_tmp.Month_Of_Quarter = row1.Month_Of_Quarter;
							zzz_tmp.Month_Of_Quarter_Name = row1.Month_Of_Quarter_Name;
							zzz_tmp.Quarter_Of_Year = row1.Quarter_Of_Year;
							zzz_tmp.Quarter_Of_Year_Name = row1.Quarter_Of_Year_Name;
							zzz_tmp.Quarter_Of_Half_Year = row1.Quarter_Of_Half_Year;
							zzz_tmp.Quarter_Of_Half_Year_Name = row1.Quarter_Of_Half_Year_Name;
							zzz_tmp.Trimester_Of_Year = row1.Trimester_Of_Year;
							zzz_tmp.Trimester_Of_Year_Name = row1.Trimester_Of_Year_Name;
							zzz_tmp.Half_Year_Of_Year = row1.Half_Year_Of_Year;
							zzz_tmp.Half_Year_Of_Year_Name = row1.Half_Year_Of_Year_Name;
							zzz = zzz_tmp;
// ###############################

						} // end of Var scope

						rejectedInnerJoin_tMap_1 = false;

						tos_count_tMap_1++;

						/**
						 * [tMap_1 main ] stop
						 */

						/**
						 * [tMap_1 process_data_begin ] start
						 */

						currentComponent = "tMap_1";

						/**
						 * [tMap_1 process_data_begin ] stop
						 */
// Start of branch "zzz"
						if (zzz != null) {

							/**
							 * [tDBOutput_1 main ] start
							 */

							currentComponent = "tDBOutput_1";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "zzz"

								);
							}

							whetherReject_tDBOutput_1 = false;
							if (zzz.PK_Date != null) {
								pstmt_tDBOutput_1.setTimestamp(1, new java.sql.Timestamp(zzz.PK_Date.getTime()));
							} else {
								pstmt_tDBOutput_1.setNull(1, java.sql.Types.TIMESTAMP);
							}

							if (zzz.Date_Name == null) {
								pstmt_tDBOutput_1.setNull(2, java.sql.Types.VARCHAR);
							} else {
								String driver = null;
								driver = "JTDS";
								if ("MSSQL_PROP".equals(driver)) {
									pstmt_tDBOutput_1.setNString(2, zzz.Date_Name);
								} else {
									pstmt_tDBOutput_1.setString(2, zzz.Date_Name);
								}
							}

							if (zzz.Year != null) {
								pstmt_tDBOutput_1.setTimestamp(3, new java.sql.Timestamp(zzz.Year.getTime()));
							} else {
								pstmt_tDBOutput_1.setNull(3, java.sql.Types.TIMESTAMP);
							}

							if (zzz.Year_Name == null) {
								pstmt_tDBOutput_1.setNull(4, java.sql.Types.VARCHAR);
							} else {
								String driver = null;
								driver = "JTDS";
								if ("MSSQL_PROP".equals(driver)) {
									pstmt_tDBOutput_1.setNString(4, zzz.Year_Name);
								} else {
									pstmt_tDBOutput_1.setString(4, zzz.Year_Name);
								}
							}

							if (zzz.Half_Year != null) {
								pstmt_tDBOutput_1.setTimestamp(5, new java.sql.Timestamp(zzz.Half_Year.getTime()));
							} else {
								pstmt_tDBOutput_1.setNull(5, java.sql.Types.TIMESTAMP);
							}

							if (zzz.Half_Year_Name == null) {
								pstmt_tDBOutput_1.setNull(6, java.sql.Types.VARCHAR);
							} else {
								String driver = null;
								driver = "JTDS";
								if ("MSSQL_PROP".equals(driver)) {
									pstmt_tDBOutput_1.setNString(6, zzz.Half_Year_Name);
								} else {
									pstmt_tDBOutput_1.setString(6, zzz.Half_Year_Name);
								}
							}

							if (zzz.Quarter != null) {
								pstmt_tDBOutput_1.setTimestamp(7, new java.sql.Timestamp(zzz.Quarter.getTime()));
							} else {
								pstmt_tDBOutput_1.setNull(7, java.sql.Types.TIMESTAMP);
							}

							if (zzz.Quarter_Name == null) {
								pstmt_tDBOutput_1.setNull(8, java.sql.Types.VARCHAR);
							} else {
								String driver = null;
								driver = "JTDS";
								if ("MSSQL_PROP".equals(driver)) {
									pstmt_tDBOutput_1.setNString(8, zzz.Quarter_Name);
								} else {
									pstmt_tDBOutput_1.setString(8, zzz.Quarter_Name);
								}
							}

							if (zzz.Trimester != null) {
								pstmt_tDBOutput_1.setTimestamp(9, new java.sql.Timestamp(zzz.Trimester.getTime()));
							} else {
								pstmt_tDBOutput_1.setNull(9, java.sql.Types.TIMESTAMP);
							}

							if (zzz.Trimester_Name == null) {
								pstmt_tDBOutput_1.setNull(10, java.sql.Types.VARCHAR);
							} else {
								String driver = null;
								driver = "JTDS";
								if ("MSSQL_PROP".equals(driver)) {
									pstmt_tDBOutput_1.setNString(10, zzz.Trimester_Name);
								} else {
									pstmt_tDBOutput_1.setString(10, zzz.Trimester_Name);
								}
							}

							if (zzz.Month != null) {
								pstmt_tDBOutput_1.setTimestamp(11, new java.sql.Timestamp(zzz.Month.getTime()));
							} else {
								pstmt_tDBOutput_1.setNull(11, java.sql.Types.TIMESTAMP);
							}

							if (zzz.Month_Name == null) {
								pstmt_tDBOutput_1.setNull(12, java.sql.Types.VARCHAR);
							} else {
								String driver = null;
								driver = "JTDS";
								if ("MSSQL_PROP".equals(driver)) {
									pstmt_tDBOutput_1.setNString(12, zzz.Month_Name);
								} else {
									pstmt_tDBOutput_1.setString(12, zzz.Month_Name);
								}
							}

							if (zzz.Day_Of_Year == null) {
								pstmt_tDBOutput_1.setNull(13, java.sql.Types.INTEGER);
							} else {
								pstmt_tDBOutput_1.setInt(13, zzz.Day_Of_Year);
							}

							if (zzz.Day_Of_Year_Name == null) {
								pstmt_tDBOutput_1.setNull(14, java.sql.Types.VARCHAR);
							} else {
								String driver = null;
								driver = "JTDS";
								if ("MSSQL_PROP".equals(driver)) {
									pstmt_tDBOutput_1.setNString(14, zzz.Day_Of_Year_Name);
								} else {
									pstmt_tDBOutput_1.setString(14, zzz.Day_Of_Year_Name);
								}
							}

							if (zzz.Day_Of_Half_Year == null) {
								pstmt_tDBOutput_1.setNull(15, java.sql.Types.INTEGER);
							} else {
								pstmt_tDBOutput_1.setInt(15, zzz.Day_Of_Half_Year);
							}

							if (zzz.Day_Of_Half_Year_Name == null) {
								pstmt_tDBOutput_1.setNull(16, java.sql.Types.VARCHAR);
							} else {
								String driver = null;
								driver = "JTDS";
								if ("MSSQL_PROP".equals(driver)) {
									pstmt_tDBOutput_1.setNString(16, zzz.Day_Of_Half_Year_Name);
								} else {
									pstmt_tDBOutput_1.setString(16, zzz.Day_Of_Half_Year_Name);
								}
							}

							if (zzz.Day_Of_Trimester == null) {
								pstmt_tDBOutput_1.setNull(17, java.sql.Types.INTEGER);
							} else {
								pstmt_tDBOutput_1.setInt(17, zzz.Day_Of_Trimester);
							}

							if (zzz.Day_Of_Trimester_Name == null) {
								pstmt_tDBOutput_1.setNull(18, java.sql.Types.VARCHAR);
							} else {
								String driver = null;
								driver = "JTDS";
								if ("MSSQL_PROP".equals(driver)) {
									pstmt_tDBOutput_1.setNString(18, zzz.Day_Of_Trimester_Name);
								} else {
									pstmt_tDBOutput_1.setString(18, zzz.Day_Of_Trimester_Name);
								}
							}

							if (zzz.Day_Of_Quarter == null) {
								pstmt_tDBOutput_1.setNull(19, java.sql.Types.INTEGER);
							} else {
								pstmt_tDBOutput_1.setInt(19, zzz.Day_Of_Quarter);
							}

							if (zzz.Day_Of_Quarter_Name == null) {
								pstmt_tDBOutput_1.setNull(20, java.sql.Types.VARCHAR);
							} else {
								String driver = null;
								driver = "JTDS";
								if ("MSSQL_PROP".equals(driver)) {
									pstmt_tDBOutput_1.setNString(20, zzz.Day_Of_Quarter_Name);
								} else {
									pstmt_tDBOutput_1.setString(20, zzz.Day_Of_Quarter_Name);
								}
							}

							if (zzz.Day_Of_Month == null) {
								pstmt_tDBOutput_1.setNull(21, java.sql.Types.INTEGER);
							} else {
								pstmt_tDBOutput_1.setInt(21, zzz.Day_Of_Month);
							}

							if (zzz.Day_Of_Month_Name == null) {
								pstmt_tDBOutput_1.setNull(22, java.sql.Types.VARCHAR);
							} else {
								String driver = null;
								driver = "JTDS";
								if ("MSSQL_PROP".equals(driver)) {
									pstmt_tDBOutput_1.setNString(22, zzz.Day_Of_Month_Name);
								} else {
									pstmt_tDBOutput_1.setString(22, zzz.Day_Of_Month_Name);
								}
							}

							if (zzz.Month_Of_Year == null) {
								pstmt_tDBOutput_1.setNull(23, java.sql.Types.INTEGER);
							} else {
								pstmt_tDBOutput_1.setInt(23, zzz.Month_Of_Year);
							}

							if (zzz.Month_Of_Year_Name == null) {
								pstmt_tDBOutput_1.setNull(24, java.sql.Types.VARCHAR);
							} else {
								String driver = null;
								driver = "JTDS";
								if ("MSSQL_PROP".equals(driver)) {
									pstmt_tDBOutput_1.setNString(24, zzz.Month_Of_Year_Name);
								} else {
									pstmt_tDBOutput_1.setString(24, zzz.Month_Of_Year_Name);
								}
							}

							if (zzz.Month_Of_Half_Year == null) {
								pstmt_tDBOutput_1.setNull(25, java.sql.Types.INTEGER);
							} else {
								pstmt_tDBOutput_1.setInt(25, zzz.Month_Of_Half_Year);
							}

							if (zzz.Month_Of_Half_Year_Name == null) {
								pstmt_tDBOutput_1.setNull(26, java.sql.Types.VARCHAR);
							} else {
								String driver = null;
								driver = "JTDS";
								if ("MSSQL_PROP".equals(driver)) {
									pstmt_tDBOutput_1.setNString(26, zzz.Month_Of_Half_Year_Name);
								} else {
									pstmt_tDBOutput_1.setString(26, zzz.Month_Of_Half_Year_Name);
								}
							}

							if (zzz.Month_Of_Trimester == null) {
								pstmt_tDBOutput_1.setNull(27, java.sql.Types.INTEGER);
							} else {
								pstmt_tDBOutput_1.setInt(27, zzz.Month_Of_Trimester);
							}

							if (zzz.Month_Of_Trimester_Name == null) {
								pstmt_tDBOutput_1.setNull(28, java.sql.Types.VARCHAR);
							} else {
								String driver = null;
								driver = "JTDS";
								if ("MSSQL_PROP".equals(driver)) {
									pstmt_tDBOutput_1.setNString(28, zzz.Month_Of_Trimester_Name);
								} else {
									pstmt_tDBOutput_1.setString(28, zzz.Month_Of_Trimester_Name);
								}
							}

							if (zzz.Month_Of_Quarter == null) {
								pstmt_tDBOutput_1.setNull(29, java.sql.Types.INTEGER);
							} else {
								pstmt_tDBOutput_1.setInt(29, zzz.Month_Of_Quarter);
							}

							if (zzz.Month_Of_Quarter_Name == null) {
								pstmt_tDBOutput_1.setNull(30, java.sql.Types.VARCHAR);
							} else {
								String driver = null;
								driver = "JTDS";
								if ("MSSQL_PROP".equals(driver)) {
									pstmt_tDBOutput_1.setNString(30, zzz.Month_Of_Quarter_Name);
								} else {
									pstmt_tDBOutput_1.setString(30, zzz.Month_Of_Quarter_Name);
								}
							}

							if (zzz.Quarter_Of_Year == null) {
								pstmt_tDBOutput_1.setNull(31, java.sql.Types.INTEGER);
							} else {
								pstmt_tDBOutput_1.setInt(31, zzz.Quarter_Of_Year);
							}

							if (zzz.Quarter_Of_Year_Name == null) {
								pstmt_tDBOutput_1.setNull(32, java.sql.Types.VARCHAR);
							} else {
								String driver = null;
								driver = "JTDS";
								if ("MSSQL_PROP".equals(driver)) {
									pstmt_tDBOutput_1.setNString(32, zzz.Quarter_Of_Year_Name);
								} else {
									pstmt_tDBOutput_1.setString(32, zzz.Quarter_Of_Year_Name);
								}
							}

							if (zzz.Quarter_Of_Half_Year == null) {
								pstmt_tDBOutput_1.setNull(33, java.sql.Types.INTEGER);
							} else {
								pstmt_tDBOutput_1.setInt(33, zzz.Quarter_Of_Half_Year);
							}

							if (zzz.Quarter_Of_Half_Year_Name == null) {
								pstmt_tDBOutput_1.setNull(34, java.sql.Types.VARCHAR);
							} else {
								String driver = null;
								driver = "JTDS";
								if ("MSSQL_PROP".equals(driver)) {
									pstmt_tDBOutput_1.setNString(34, zzz.Quarter_Of_Half_Year_Name);
								} else {
									pstmt_tDBOutput_1.setString(34, zzz.Quarter_Of_Half_Year_Name);
								}
							}

							if (zzz.Trimester_Of_Year == null) {
								pstmt_tDBOutput_1.setNull(35, java.sql.Types.INTEGER);
							} else {
								pstmt_tDBOutput_1.setInt(35, zzz.Trimester_Of_Year);
							}

							if (zzz.Trimester_Of_Year_Name == null) {
								pstmt_tDBOutput_1.setNull(36, java.sql.Types.VARCHAR);
							} else {
								String driver = null;
								driver = "JTDS";
								if ("MSSQL_PROP".equals(driver)) {
									pstmt_tDBOutput_1.setNString(36, zzz.Trimester_Of_Year_Name);
								} else {
									pstmt_tDBOutput_1.setString(36, zzz.Trimester_Of_Year_Name);
								}
							}

							if (zzz.Half_Year_Of_Year == null) {
								pstmt_tDBOutput_1.setNull(37, java.sql.Types.INTEGER);
							} else {
								pstmt_tDBOutput_1.setInt(37, zzz.Half_Year_Of_Year);
							}

							if (zzz.Half_Year_Of_Year_Name == null) {
								pstmt_tDBOutput_1.setNull(38, java.sql.Types.VARCHAR);
							} else {
								String driver = null;
								driver = "JTDS";
								if ("MSSQL_PROP".equals(driver)) {
									pstmt_tDBOutput_1.setNString(38, zzz.Half_Year_Of_Year_Name);
								} else {
									pstmt_tDBOutput_1.setString(38, zzz.Half_Year_Of_Year_Name);
								}
							}

							pstmt_tDBOutput_1.addBatch();
							nb_line_tDBOutput_1++;

							batchSizeCounter_tDBOutput_1++;

							////////// batch execute by batch size///////
							class LimitBytesHelper_tDBOutput_1 {
								public int limitBytePart1(int counter, java.sql.PreparedStatement pstmt_tDBOutput_1)
										throws Exception {
									try {

										for (int countEach_tDBOutput_1 : pstmt_tDBOutput_1.executeBatch()) {
											if (countEach_tDBOutput_1 == -2 || countEach_tDBOutput_1 == -3) {
												break;
											}
											counter += countEach_tDBOutput_1;
										}

									} catch (java.sql.BatchUpdateException e) {
										globalMap.put("tDBOutput_1_ERROR_MESSAGE", e.getMessage());

										int countSum_tDBOutput_1 = 0;
										for (int countEach_tDBOutput_1 : e.getUpdateCounts()) {
											counter += (countEach_tDBOutput_1 < 0 ? 0 : countEach_tDBOutput_1);
										}

										System.err.println(e.getMessage());

									}
									return counter;
								}

								public int limitBytePart2(int counter, java.sql.PreparedStatement pstmt_tDBOutput_1)
										throws Exception {
									try {

										for (int countEach_tDBOutput_1 : pstmt_tDBOutput_1.executeBatch()) {
											if (countEach_tDBOutput_1 == -2 || countEach_tDBOutput_1 == -3) {
												break;
											}
											counter += countEach_tDBOutput_1;
										}

									} catch (java.sql.BatchUpdateException e) {
										globalMap.put("tDBOutput_1_ERROR_MESSAGE", e.getMessage());

										for (int countEach_tDBOutput_1 : e.getUpdateCounts()) {
											counter += (countEach_tDBOutput_1 < 0 ? 0 : countEach_tDBOutput_1);
										}

										System.err.println(e.getMessage());

									}
									return counter;
								}
							}
							if ((batchSize_tDBOutput_1 > 0)
									&& (batchSize_tDBOutput_1 <= batchSizeCounter_tDBOutput_1)) {

								insertedCount_tDBOutput_1 = new LimitBytesHelper_tDBOutput_1()
										.limitBytePart1(insertedCount_tDBOutput_1, pstmt_tDBOutput_1);
								rowsToCommitCount_tDBOutput_1 = insertedCount_tDBOutput_1;

								batchSizeCounter_tDBOutput_1 = 0;
							}

							//////////// commit every////////////

							commitCounter_tDBOutput_1++;
							if (commitEvery_tDBOutput_1 <= commitCounter_tDBOutput_1) {
								if ((batchSize_tDBOutput_1 > 0) && (batchSizeCounter_tDBOutput_1 > 0)) {

									insertedCount_tDBOutput_1 = new LimitBytesHelper_tDBOutput_1()
											.limitBytePart1(insertedCount_tDBOutput_1, pstmt_tDBOutput_1);

									batchSizeCounter_tDBOutput_1 = 0;
								}
								if (rowsToCommitCount_tDBOutput_1 != 0) {

								}
								conn_tDBOutput_1.commit();
								if (rowsToCommitCount_tDBOutput_1 != 0) {

									rowsToCommitCount_tDBOutput_1 = 0;
								}
								commitCounter_tDBOutput_1 = 0;
							}

							tos_count_tDBOutput_1++;

							/**
							 * [tDBOutput_1 main ] stop
							 */

							/**
							 * [tDBOutput_1 process_data_begin ] start
							 */

							currentComponent = "tDBOutput_1";

							/**
							 * [tDBOutput_1 process_data_begin ] stop
							 */

							/**
							 * [tDBOutput_1 process_data_end ] start
							 */

							currentComponent = "tDBOutput_1";

							/**
							 * [tDBOutput_1 process_data_end ] stop
							 */

						} // End of branch "zzz"

						/**
						 * [tMap_1 process_data_end ] start
						 */

						currentComponent = "tMap_1";

						/**
						 * [tMap_1 process_data_end ] stop
						 */

						/**
						 * [tDBInput_1 process_data_end ] start
						 */

						currentComponent = "tDBInput_1";

						/**
						 * [tDBInput_1 process_data_end ] stop
						 */

						/**
						 * [tDBInput_1 end ] start
						 */

						currentComponent = "tDBInput_1";

					}
				} finally {
					if (rs_tDBInput_1 != null) {
						rs_tDBInput_1.close();
					}
					if (stmt_tDBInput_1 != null) {
						stmt_tDBInput_1.close();
					}
					if (conn_tDBInput_1 != null && !conn_tDBInput_1.isClosed()) {

						conn_tDBInput_1.close();

						if ("com.mysql.cj.jdbc.Driver".equals((String) globalMap.get("driverClass_"))
								&& routines.system.BundleUtils.inOSGi()) {
							Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread")
									.getMethod("checkedShutdown").invoke(null, (Object[]) null);
						}

					}
				}
				globalMap.put("tDBInput_1_NB_LINE", nb_line_tDBInput_1);

				ok_Hash.put("tDBInput_1", true);
				end_Hash.put("tDBInput_1", System.currentTimeMillis());

				/**
				 * [tDBInput_1 end ] stop
				 */

				/**
				 * [tMap_1 end ] start
				 */

				currentComponent = "tMap_1";

// ###############################
// # Lookup hashes releasing
// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row1");
				}

				ok_Hash.put("tMap_1", true);
				end_Hash.put("tMap_1", System.currentTimeMillis());

				/**
				 * [tMap_1 end ] stop
				 */

				/**
				 * [tDBOutput_1 end ] start
				 */

				currentComponent = "tDBOutput_1";

				try {
					int countSum_tDBOutput_1 = 0;
					if (pstmt_tDBOutput_1 != null && batchSizeCounter_tDBOutput_1 > 0) {

						for (int countEach_tDBOutput_1 : pstmt_tDBOutput_1.executeBatch()) {
							if (countEach_tDBOutput_1 == -2 || countEach_tDBOutput_1 == -3) {
								break;
							}
							countSum_tDBOutput_1 += countEach_tDBOutput_1;
						}
						rowsToCommitCount_tDBOutput_1 += countSum_tDBOutput_1;

					}

					insertedCount_tDBOutput_1 += countSum_tDBOutput_1;

				} catch (java.sql.BatchUpdateException e) {
					globalMap.put("tDBOutput_1_ERROR_MESSAGE", e.getMessage());

					int countSum_tDBOutput_1 = 0;
					for (int countEach_tDBOutput_1 : e.getUpdateCounts()) {
						countSum_tDBOutput_1 += (countEach_tDBOutput_1 < 0 ? 0 : countEach_tDBOutput_1);
					}
					rowsToCommitCount_tDBOutput_1 += countSum_tDBOutput_1;

					insertedCount_tDBOutput_1 += countSum_tDBOutput_1;

					System.err.println(e.getMessage());

				}
				if (pstmt_tDBOutput_1 != null) {

					pstmt_tDBOutput_1.close();
					resourceMap.remove("pstmt_tDBOutput_1");

				}
				resourceMap.put("statementClosed_tDBOutput_1", true);
				if (rowsToCommitCount_tDBOutput_1 != 0) {

				}
				conn_tDBOutput_1.commit();
				if (rowsToCommitCount_tDBOutput_1 != 0) {

					rowsToCommitCount_tDBOutput_1 = 0;
				}
				commitCounter_tDBOutput_1 = 0;
				conn_tDBOutput_1.close();
				resourceMap.put("finish_tDBOutput_1", true);

				nb_line_deleted_tDBOutput_1 = nb_line_deleted_tDBOutput_1 + deletedCount_tDBOutput_1;
				nb_line_update_tDBOutput_1 = nb_line_update_tDBOutput_1 + updatedCount_tDBOutput_1;
				nb_line_inserted_tDBOutput_1 = nb_line_inserted_tDBOutput_1 + insertedCount_tDBOutput_1;
				nb_line_rejected_tDBOutput_1 = nb_line_rejected_tDBOutput_1 + rejectedCount_tDBOutput_1;

				globalMap.put("tDBOutput_1_NB_LINE", nb_line_tDBOutput_1);
				globalMap.put("tDBOutput_1_NB_LINE_UPDATED", nb_line_update_tDBOutput_1);
				globalMap.put("tDBOutput_1_NB_LINE_INSERTED", nb_line_inserted_tDBOutput_1);
				globalMap.put("tDBOutput_1_NB_LINE_DELETED", nb_line_deleted_tDBOutput_1);
				globalMap.put("tDBOutput_1_NB_LINE_REJECTED", nb_line_rejected_tDBOutput_1);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "zzz");
				}

				ok_Hash.put("tDBOutput_1", true);
				end_Hash.put("tDBOutput_1", System.currentTimeMillis());

				/**
				 * [tDBOutput_1 end ] stop
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
				 * [tDBInput_1 finally ] start
				 */

				currentComponent = "tDBInput_1";

				/**
				 * [tDBInput_1 finally ] stop
				 */

				/**
				 * [tMap_1 finally ] start
				 */

				currentComponent = "tMap_1";

				/**
				 * [tMap_1 finally ] stop
				 */

				/**
				 * [tDBOutput_1 finally ] start
				 */

				currentComponent = "tDBOutput_1";

				try {
					if (resourceMap.get("statementClosed_tDBOutput_1") == null) {
						java.sql.PreparedStatement pstmtToClose_tDBOutput_1 = null;
						if ((pstmtToClose_tDBOutput_1 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmt_tDBOutput_1")) != null) {
							pstmtToClose_tDBOutput_1.close();
						}
					}
				} finally {
					if (resourceMap.get("finish_tDBOutput_1") == null) {
						java.sql.Connection ctn_tDBOutput_1 = null;
						if ((ctn_tDBOutput_1 = (java.sql.Connection) resourceMap.get("conn_tDBOutput_1")) != null) {
							try {
								ctn_tDBOutput_1.close();
							} catch (java.sql.SQLException sqlEx_tDBOutput_1) {
								String errorMessage_tDBOutput_1 = "failed to close the connection in tDBOutput_1 :"
										+ sqlEx_tDBOutput_1.getMessage();
								System.err.println(errorMessage_tDBOutput_1);
							}
						}
					}
				}

				/**
				 * [tDBOutput_1 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBInput_1_SUBPROCESS_STATE", 1);
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
		final Time TimeClass = new Time();

		int exitCode = TimeClass.runJobInTOS(args);

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
			java.io.InputStream inContext = Time.class.getClassLoader()
					.getResourceAsStream("orange/time_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = Time.class.getClassLoader()
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
			tDBInput_1Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tDBInput_1) {
			globalMap.put("tDBInput_1_SUBPROCESS_STATE", -1);

			e_tDBInput_1.printStackTrace();

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if (false) {
			System.out.println((endUsedMemory - startUsedMemory) + " bytes memory increase when running : Time");
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
 * 127736 characters generated by Talend Open Studio for Data Integration on the
 * 12 mai 2022 à 18:48:01 CEST
 ************************************************************************************************/