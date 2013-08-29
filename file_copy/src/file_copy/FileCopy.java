package file_copy;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileCopy {
	static final int MAX = 512;
	
	public static void main(String[] args) throws IOException {

		String sourcePath = new String();
		String targetPath = new String();
		File sourceFile;
		File targetFile;
		FileInputStream fis;
		FileOutputStream fos;
		 
		// 파일 목록을 가져와서 일치 하는것이 있나 확인하기

		System.out.println("복사를 위한 소스파일 경로를 입력해 주세요.");
		BufferedReader brSource = new BufferedReader(new InputStreamReader(System.in));
		sourcePath = brSource.readLine();
//		sourcePath = "source.txt";

		sourceFile = new File(sourcePath);
		
		// 파일이 디렉토리라면 종료
		if(sourceFile.isDirectory()) {
			System.out.println("디렉토리는 복사가 불가합니다. 프로세스를 종료합니다.");
			return;				
		}
		
		if(sourceFile.exists()) {
			System.out.println("파일 찾음 ㅋ");
			// 존재하는 파일을 이용해 FileInputStream을 생성한다.
			fis = new FileInputStream(sourcePath);	
		} else {
			System.out.println("소스파일주소가 올바르지 않거나, 파일이 존재하지 않습니다. 프로세스를 종료합니다.");
			return;
		}

		System.out.println("복사를 위한 타겟파일 경로를 입력해 주세요.");
//		targetPath = "target.txt";
		BufferedReader brTarget = new BufferedReader(new InputStreamReader(System.in));
		targetPath = brTarget.readLine();
		
		targetFile = new File(targetPath);
		
		// 목표지역이 디렉토리라면 종료
		if(targetFile.isDirectory()) {
			System.out.println("디렉토리로는 복사가 불가합니다. 프로세스를 종료합니다.");
			return;				
		}
		
		fos = new FileOutputStream(targetFile);	
	
		// FileInputStream에서 1byte 씩 읽는다. => int data = FileInputStream.read()
		// 읽은 데이터가 -1이 될 때까지 FileOutputStream에 쓰기한다. => write(data)
		while(true) {
			int data = fis.read();
			System.out.println("result : " + data + " : " + (char)data);
			if(data==-1) 
				break;
			fos.write(data);
		}
	
		fos.close();
		fis.close();

	}
}
