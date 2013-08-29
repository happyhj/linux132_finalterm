package findFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class FindFile {
	static final int MAX = 512;
	
	public static File searchTree(File directory ,String fileName) {
		boolean isDirectoryExist = false;
		File[] filelist = new File[MAX];	
		if(directory.isDirectory()) {
			filelist = directory.listFiles();
		} else {
			System.err.printf("%s is not dir", directory.getName());
			System.exit(1);
		}

		if(filelist == null) {
			System.out.println("빈디렉토리이거나 권한없습니다.");	
			return null;
		}
		
//		System.out.println(directory.getAbsolutePath());	
		// 입력받은 디렉토리에서 같은이름을 가진 파일이 있는지 살핀다. 있다면 콘솔에 출력
		for( File aFile : filelist) {


			if(aFile.getName().equals(fileName))
				System.out.println(aFile.getAbsolutePath());
		}
		
		// 종료조건 - 입력받은 디렉토리내에서 디렉토리가 존재하지 않으면 종료
		for( File aFile : filelist) {
			if(aFile.isDirectory()) {
				isDirectoryExist = true;
				break;
			}
		}		
		if(isDirectoryExist==false) 
			return null;
			
		// 서브 디렉토리만 대상으로 재귀호출 수행
		for (File subDirectory: filelist) {
			if(subDirectory.isDirectory())
				searchTree(subDirectory,fileName);			
		}
		
		return directory;
	}
	
	public static void main(String[] args) throws IOException {
		String sTargetPath = new String();
		String sTargetFileName = new String();
		File targetPath;

		System.out.println("탐색할 경로를 입력해 주세요.");
		BufferedReader brTargetPath = new BufferedReader(new InputStreamReader(System.in));
		sTargetPath = brTargetPath.readLine();	
		targetPath = new File(sTargetPath);
		
		if(!targetPath.isDirectory()) {
			System.out.println("디렉토리만 탐색이 가능하니다. 종료합니다.");
			return;				
		}
		if(!targetPath.exists()) {
			System.out.println("경로주소가 올바르지 않거나, 존재하지 않습니다. 프로세스를 종료합니다.");
			return;
		}
		
		System.out.println("찾을 파일이름을 입력해 주세요.");
		BufferedReader brTargetFileName = new BufferedReader(new InputStreamReader(System.in));
		sTargetFileName = brTargetFileName.readLine();

		searchTree(targetPath ,sTargetFileName);
	}
}
