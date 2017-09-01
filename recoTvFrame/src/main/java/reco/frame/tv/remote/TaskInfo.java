package reco.frame.tv.remote;

public class TaskInfo {

	// ����״̬
	public static final int INIT = 0; // ��ʼ״̬
	public static final int WAITING = 1; // �ȴ�����
	public static final int RUNNING = 2; // ��������
	public static final int CANCELED = 3; // ȡ��
	public static final int SUCCESS = 4; // ���سɹ�
	public static final int FAILED = 5;
	public static final int INSTALLED = 6;

	private int taskId;
	private String taskName;
	private int progress;
	private int status;
	private String url;
	private String packageName;
	private int fileSize;
	private String filePath;

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	private int downloadSize;
	private String downloadUrl;

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public int getFileSize() {
		return fileSize;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	public int getDownloadSize() {
		return downloadSize;
	}

	public void setDownloadSize(int downloadSize) {
		this.downloadSize = downloadSize;
	}

	@Override
	public String toString() {
		return "TaskInfo [taskName=" + taskName + ", progress=" + progress
				+ ", status=" + status + ", url=" + url + ", packageName="
				+ packageName + ", fileSize=" + fileSize + ", filePath="
				+ filePath + "downloadSize=" + downloadSize + ", downloadUrl="
				+ downloadUrl + "]";
	}

}
