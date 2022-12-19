package com.wadhams.music.reorg.app

import org.jaudiotagger.audio.AudioFile
import org.jaudiotagger.audio.AudioFileIO
import org.jaudiotagger.tag.Tag
import org.jaudiotagger.tag.FieldKey

class MusicApp {
    static void main(String[] args) {
		println 'MusicApp started...'
		println ''
		
		MusicApp app = new MusicApp()
		app.execute()

		println 'MusicApp ended.'
    }
	
	def execute() {
		File baseDir = new File('C:\\Temp\\WMA_Music')
		
		baseDir.eachFile {f ->
			report(f)
		}
	}

	def report(File f) {
		AudioFile audioFile = AudioFileIO.read(f)
		Tag tag = audioFile.getTag()
		if (tag != null) {
//			def iter = tag.getFields()
//			iter.each {
//				println it
//			}
//			println '------------------------------------------'
			
			//Title
			String title = tag.getFirst(FieldKey.TITLE)
			println "title....: $title"
			
			//Artist
			String artist = tag.getFirst(FieldKey.ARTIST)
			println "artist...: $artist"
			
//			metaData.setAlbumName(getTagField(tag,FieldKey.ALBUM));
//			metaData.setTitle(getTagField(tag,FieldKey.TITLE));
//			metaData.setYear(parseInteger(getTagField(tag,FieldKey.YEAR)));
//			metaData.setGenre(mapGenre(getTagField(tag,FieldKey.GENRE)));
//			metaData.setDiscNumber(parseInteger(getTagField(tag,FieldKey.DISC_NO)));
//			metaData.setTrackNumber(parseTrackNumber(getTagField(tag,FieldKey.TRACK)));
//			String songArtist=getTagField(tag,FieldKey.ARTIST);
//			String albumArtist=getTagField(tag,FieldKey.ALBUM_ARTIST);
//			metaData.setArtist(StringUtils.isBlank(albumArtist) ? songArtist : albumArtist);
			
			println ''
		}

		println ''
	}
}
