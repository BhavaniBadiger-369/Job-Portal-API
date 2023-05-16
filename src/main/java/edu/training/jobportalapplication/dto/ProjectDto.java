package edu.training.jobportalapplication.dto;

public class ProjectDto {

	    private long projectId;
		private String projectTitle;
		private  String projectDescription;
		private String projectURL;
		
		public long getProjectId() {
			return projectId;
		}
		public void setProjectId(long projectId) {
			this.projectId = projectId;
		}
		public String getProjectTitle() {
			return projectTitle;
		}
		public void setProjectTitle(String projectTitle) {
			this.projectTitle = projectTitle;
		}
		public String getProjectDescription() {
			return projectDescription;
		}
		public void setProjectDescription(String projectDescription) {
			this.projectDescription = projectDescription;
		}
		public String getProjectURL() {
			return projectURL;
		}
		public void setProjectURL(String projectURL) {
			this.projectURL = projectURL;
		}
		
		
}
